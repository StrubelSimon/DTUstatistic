package io.strubel.DTUstatistic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.strubel.DTUstatistic.model.DailyYield;
import io.strubel.DTUstatistic.service.DailyYieldService;

import java.util.List;

@RestController
@RequestMapping("/api/daily-yield")
public class DailyYieldController {

    @Autowired
    private DailyYieldService dailyYieldService;

    @GetMapping
    public List<DailyYield> getAll() {
        return dailyYieldService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyYield> getById(@PathVariable String id) {
        return dailyYieldService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DailyYield create(@RequestBody DailyYield dailyYield) {
        return dailyYieldService.create(dailyYield);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DailyYield> update(@PathVariable String id, @RequestBody DailyYield updatedYield) {
        return dailyYieldService.update(id, updatedYield)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        return dailyYieldService.delete(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}