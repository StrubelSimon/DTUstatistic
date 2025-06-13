package io.strubel.DTUstatistic.repository;

import io.strubel.DTUstatistic.model.Inverter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InverterRepository extends MongoRepository<Inverter, String> {
    boolean existsBySerial(String serial);
}