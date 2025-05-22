package io.strubel.DTUstatistic.repository;

import io.strubel.DTUstatistic.model.DailyYield;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DailyYieldRepository extends MongoRepository<DailyYield, String> {

}