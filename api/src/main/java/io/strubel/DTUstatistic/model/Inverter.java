package io.strubel.DTUstatistic.model;

public class Inverter {

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