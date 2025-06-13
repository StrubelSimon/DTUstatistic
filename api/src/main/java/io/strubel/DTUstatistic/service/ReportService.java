package io.strubel.DTUstatistic.service;

import io.strubel.DTUstatistic.dto.InverterSummary;
import io.strubel.DTUstatistic.model.DailyYield;
import io.strubel.DTUstatistic.repository.DailyYieldRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service zur Erstellung von Tages-, Monats- und Jahresberichten
 * basierend auf den Ertragsdaten der Wechselrichter.
 * 
 * Berichte werden als Map mit Seriennummern und Ertragszusammenfassungen
 * geliefert.
 * 
 * @author Strubel
 * @version 1.0
 */
@Service
public class ReportService {

    @Autowired
    private DailyYieldRepository dailyYieldRepository;

    /**
     * Liefert einen Tagesreport aller Wechselrichter für das angegebene Datum.
     * Die Erträge werden aus der Datenbank geladen und zusammengefasst.
     * 
     * @param day Datum im Format ddMMyyyy
     * @return Map mit Seriennummer und Ertragszusammenfassung pro Wechselrichter
     */
    public Map<String, InverterSummary> getDailyReport(String day) {
        return dailyYieldRepository.findByDay(day)
                .map(dailyYield -> aggregateInverterYields(List.of(dailyYield)))
                .orElseGet(() -> {
                    return Collections.emptyMap();
                });
    }

    /**
     * Liefert einen Monatsreport aller Wechselrichter für den angegebenen Monat.
     * Alle Tagesdaten des Monats werden aggregiert und aufsummiert.
     * 
     * @param month Monat im Format MMyyyy
     * @return Map mit Seriennummer und aufsummierten Monatswerten
     */
    public Map<String, InverterSummary> getMonthlyReport(String month) {
        List<DailyYield> monthlyData = dailyYieldRepository.findByDayEndingWith(month);
        return aggregateInverterYields(monthlyData);
    }

    /**
     * Liefert einen Jahresreport aller Wechselrichter für das angegebene Jahr.
     * Alle Tagesdaten des Jahres werden aggregiert und aufsummiert.
     * 
     * @param year Jahr im Format yyyy
     * @return Map mit Seriennummer und aufsummierten Jahreswerten
     */
    public Map<String, InverterSummary> getYearlyReport(String year) {
        List<DailyYield> yearlyData = dailyYieldRepository.findByDayEndingWithIgnoreCase(year);
        return aggregateInverterYields(yearlyData);
    }

    /**
     * Fasst Ertragsdaten aus mehreren Tagen zusammen.
     * Summiert die Werte je Wechselrichter anhand der Seriennummer.
     * 
     * @param yields Liste von DailyYield-Objekten
     * @return Map mit Seriennummer und zusammengefasstem Ertrag
     */
    private Map<String, InverterSummary> aggregateInverterYields(List<DailyYield> yields) {
        Map<String, InverterSummary> inverterYieldMap = new HashMap<>();

        for (DailyYield yieldData : yields) {

            if (yieldData.getInverters() == null)
                continue;

            for (DailyYield.Inverter inverter : yieldData.getInverters()) {
                if (inverter.getYieldToday() == null || inverter.getSerial() == null)
                    continue;

                String serial = inverter.getSerial().trim();
                String name = inverter.getName();
                int yield = inverter.getYieldToday().getV();

                inverterYieldMap.compute(serial, (key, summary) -> {
                    if (summary == null) {
                        return new InverterSummary(name, serial, yield);
                    } else {
                        summary.addYield(yield);
                        System.out.println(summary);
                        return summary;
                    }
                });
            }
        }

        return inverterYieldMap;
    }
}
