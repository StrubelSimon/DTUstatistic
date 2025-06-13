package io.strubel.DTUstatistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.strubel.DTUstatistic.model.DailyYield;
import io.strubel.DTUstatistic.model.Inverter;
import io.strubel.DTUstatistic.repository.DailyYieldRepository;
import io.strubel.DTUstatistic.repository.InverterRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service zur Verwaltung und Aktualisierung täglicher Ertragsdaten der
 * Wechselrichter.
 * 
 * Stellt Methoden zum Abruf (täglich, monatlich, jährlich) und zur
 * Synchronisation mit externer API bereit.
 * 
 * Persistiert die Daten in MongoDB.
 * 
 * @author Strubel
 * @version 1.0
 */
@Service
public class DailyYieldService {

    @Autowired
    private InverterRepository inverterRepository;

    @Autowired
    private DailyYieldRepository dailyYieldRepository;

    final RestTemplate restTemplate = new RestTemplate();

    @Value("${api.openDTU}")
    String apiUrl;

    /**
     * Liefert die DailyYield-Daten zu einer eindeutigen ID zurück.
     * 
     * @param id die eindeutige ID des DailyYield-Datensatzes
     * @return Optional mit dem DailyYield-Datensatz, falls vorhanden
     */
    public Optional<DailyYield> getById(String id) {
        return dailyYieldRepository.findById(id);
    }

    /**
     * Liefert die DailyYield-Daten für einen bestimmten Tag zurück.
     * 
     * @param day der Tag im Format "ddMMyyyy"
     * @return Optional mit dem DailyYield-Datensatz für den Tag, falls vorhanden
     */
    public Optional<DailyYield> getDailyData(String day) {
        return dailyYieldRepository.findByDay(day);
    }

    /**
     * Liefert eine Liste von DailyYield-Daten für alle Tage eines Monats.
     * 
     * @param month der Monat als String, typischerweise "MMyyyy"
     * @return Liste von DailyYield-Datensätzen des Monats
     */
    public List<DailyYield> getMonthlyData(String month) {
        return dailyYieldRepository.findByDayEndingWith(month);
    }

    /**
     * Liefert eine Liste von DailyYield-Daten für alle Tage eines Jahres.
     * 
     * @param year das Jahr als String, z.B. "2025"
     * @return Liste von DailyYield-Datensätzen des Jahres
     */
    public List<DailyYield> getYearlyData(String year) {
        return dailyYieldRepository.findByDayEndingWithIgnoreCase(year);
    }

    /**
     * Holt die aktuellen Tagesdaten von allen im System registrierten
     * Wechselrichtern
     * über die externe API und aktualisiert die lokalen DailyYield-Datensätze in
     * der DB.
     */
    public void fetchAndUpdateDailyData() {

        List<Inverter> inverters = inverterRepository.findAll();
        if (!inverters.isEmpty()) {

            for (Inverter inverter : inverters) {
                List<DailyYield.Inverter> inverterData = fetchDataBySerial(inverter.getSerial());
                this.saveInverterYieldData(inverterData);
            }
        }
    }

    /**
     * Speichert oder aktualisiert die DailyYield-Daten der Wechselrichter für den
     * aktuellen Tag.
     * Wenn für den heutigen Tag bereits Daten existieren, werden diese aktualisiert
     * oder ergänzt.
     * 
     * @param inverterList Liste der Wechselrichterdaten, die gespeichert werden
     *                     sollen
     */
    private void saveInverterYieldData(List<DailyYield.Inverter> inverterList) {

        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));

        DailyYield dailyYield = dailyYieldRepository.findByDay(today)
                .orElseGet(() -> {
                    DailyYield newYield = new DailyYield();
                    newYield.setDay(today);
                    newYield.setInverters(new ArrayList<>());
                    return newYield;
                });

        if (dailyYield.getInverters() == null) {
            dailyYield.setInverters(new ArrayList<>());
        }

        for (DailyYield.Inverter inv : inverterList) {
            boolean updated = false;

            for (int i = 0; i < dailyYield.getInverters().size(); i++) {
                if (dailyYield.getInverters().get(i).getSerial().equals(inv.getSerial())) {
                    dailyYield.getInverters().set(i, inv);
                    updated = true;
                    break;
                }
            }

            if (!updated) {
                dailyYield.getInverters().add(inv);
            }
        }

        dailyYieldRepository.save(dailyYield);
    }

    /**
     * Ruft über die externe API die Live-Daten eines Wechselrichters anhand seiner
     * Seriennummer ab.
     * 
     * @param serial die Seriennummer des Wechselrichters
     * @return Liste der Inverter-Daten für den angegebenen Wechselrichter
     */
    private List<DailyYield.Inverter> fetchDataBySerial(String serial) {
        List<DailyYield.Inverter> inverterList = new ArrayList<>();
        try {
            String response = restTemplate.getForObject(apiUrl + "/livedata/status?inv=" + serial, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(response);
            JsonNode invertersNode = root.get("inverters");

            for (JsonNode inverterNode : invertersNode) {
                DailyYield.Inverter inverter = new DailyYield.Inverter();
                inverter.setSerial(inverterNode.get("serial").asText());
                inverter.setName(inverterNode.get("name").asText());

                JsonNode yieldDayNode = inverterNode.path("INV").path("0").path("YieldDay");

                if (!yieldDayNode.isMissingNode()) {
                    DailyYield.YieldToday yieldToday = new DailyYield.YieldToday();
                    yieldToday.setV(yieldDayNode.path("v").asInt());
                    yieldToday.setU(yieldDayNode.path("u").asText());
                    yieldToday.setD(yieldDayNode.path("d").asInt());

                    inverter.setYieldToday(yieldToday);
                }

                inverterList.add(inverter);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inverterList;
    }

}
