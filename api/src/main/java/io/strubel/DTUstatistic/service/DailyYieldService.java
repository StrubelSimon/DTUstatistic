package io.strubel.DTUstatistic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.strubel.DTUstatistic.model.DailyYield;
import io.strubel.DTUstatistic.repository.DailyYieldRepository;

import java.util.List;
import java.util.Optional;

@Service

public class DailyYieldService {
    @Autowired
    private DailyYieldRepository dailyYieldRepository;

    public List<DailyYield> getAll() {
        return dailyYieldRepository.findAll();
    }

    public Optional<DailyYield> getById(String id) {
        return dailyYieldRepository.findById(id);
    }

    public DailyYield create(DailyYield dailyYield) {
        return dailyYieldRepository.save(dailyYield);
    }

    public Optional<DailyYield> update(String id, DailyYield updatedYield) {
        return dailyYieldRepository.findById(id).map(existing -> {
            updatedYield.setId(id); // sicherstellen, dass die ID erhalten bleibt
            return dailyYieldRepository.save(updatedYield);
        });
    }

    public boolean delete(String id) {
        if (dailyYieldRepository.existsById(id)) {
            dailyYieldRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
