package io.strubel.DTUstatistic.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
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

    @Indexed(unique = true)
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

    public static class Inverter {

        private String serial;
        private String name;
        private YieldToday yieldToday;

        // Getter und Setter

        public String getSerial() {
            return serial;
        }

        public void setSerial(String serial) {
            this.serial = serial;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public YieldToday getYieldToday() {
            return yieldToday;
        }

        public void setYieldToday(YieldToday yieldToday) {
            this.yieldToday = yieldToday;
        }
    }

    public static class YieldToday {

        private int v;
        private String u;
        private int d;

        // Getter und Setter

        public int getV() {
            return v;
        }

        public void setV(int v) {
            this.v = v;
        }

        public String getU() {
            return u;
        }

        public void setU(String u) {
            this.u = u;
        }

        public int getD() {
            return d;
        }

        public void setD(int d) {
            this.d = d;
        }
    }
}