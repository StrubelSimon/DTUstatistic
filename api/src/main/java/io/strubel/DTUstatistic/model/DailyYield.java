package io.strubel.DTUstatistic.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "DailyYield")
public class DailyYield {

    @Id
    @Field("_id")
    private String id = UUID.randomUUID().toString();

    @Field("day")
    private String day;

    @Field("inverters")
    private List<Inverter> inverters;

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    public DailyYield() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = new Date();
    }

    // Getter und Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Inverter> getInverters() {
        return inverters;
    }

    public void setInverters(List<Inverter> inverters) {
        this.inverters = inverters;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
