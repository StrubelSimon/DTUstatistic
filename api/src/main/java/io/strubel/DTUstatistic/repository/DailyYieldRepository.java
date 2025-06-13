package io.strubel.DTUstatistic.repository;

import io.strubel.DTUstatistic.model.DailyYield;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DailyYieldRepository extends MongoRepository<DailyYield, String> {
    Optional<DailyYield> findByDay(String day);

    List<DailyYield> findByDayEndingWith(String month);

    List<DailyYield> findByDayEndingWithIgnoreCase(String year);

    // "\\d{2}062025"
    // List<DailyYield> findByDayRegex(String regex);
}