package io.strubel.DTUstatistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.strubel.DTUstatistic.model.Inverter;
import io.strubel.DTUstatistic.repository.InverterRepository;

/**
 * Service zur Verwaltung von Wechselrichter-Daten (Inverter).
 * 
 * Lädt Wechselrichterdaten von einer externen API und speichert
 * neue Einträge in der Datenbank.
 * 
 * Nutzt RestTemplate für API-Aufrufe und InverterRepository für
 * Persistenz.
 * 
 * @author Strubel
 * @version 1.0
 */
@Service
public class InverterService {

    @Autowired
    private InverterRepository inverterRepository;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${api.openDTU}")
    private String apiUrl;

    /**
     * Holt Wechselrichterdaten von der externen API und speichert
     * neue Wechselrichter, falls sie noch nicht in der Datenbank existieren.
     * 
     * Fehler beim Abruf oder Verarbeiten werden ausgegeben (ggf. Logging
     * verwenden).
     */
    public void fetchAndSaveInverters() {

        try {
            String response = restTemplate.getForObject(apiUrl + "/livedata/status", String.class);
            JsonNode root = objectMapper.readTree(response);
            JsonNode inverters = root.path("inverters");

            for (JsonNode inverterNode : inverters) {
                String serial = inverterNode.path("serial").asText();
                String name = inverterNode.path("name").asText();

                if (!inverterRepository.existsBySerial(serial)) {
                    Inverter inverter = new Inverter(name, serial);
                    inverterRepository.save(inverter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // ggf. Logging verwenden
        }
    }

}
