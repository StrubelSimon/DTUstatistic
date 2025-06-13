package io.strubel.DTUstatistic.model;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Inverters")
public class Inverter {

    @Id
    @Field("_id")
    private String id = UUID.randomUUID().toString();

    private String name;
    private String serial;

    // Standardkonstruktor
    public Inverter() {
        this.id = UUID.randomUUID().toString();
    }

    public Inverter(String name, String serial) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.serial = serial;
    }

    // Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
