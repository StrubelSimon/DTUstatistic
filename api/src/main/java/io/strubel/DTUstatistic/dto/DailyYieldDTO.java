package io.strubel.DTUstatistic.dto;

import java.util.Date;
import java.util.List;

public class DailyYieldDTO {
    private String id;
    private String day;
    private List<InverterDTO> inverters;
    private Date createdAt;
    private Date updatedAt;

    public DailyYieldDTO() {
    }

    public DailyYieldDTO(String id, String day, List<InverterDTO> inverters, Date createdAt, Date updatedAt) {
        this.id = id;
        this.day = day;
        this.inverters = inverters;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

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

    public List<InverterDTO> getInverters() {
        return inverters;
    }

    public void setInverters(List<InverterDTO> inverters) {
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

    @Override
    public String toString() {
        return "DailyYieldDTO{" +
                "id='" + id + '\'' +
                ", day='" + day + '\'' +
                ", inverters=" + inverters +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
