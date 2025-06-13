package io.strubel.DTUstatistic.dto;

public class InverterDTO {
    private String serial;
    private String name;
    private YieldTodayDTO yieldToday;

    public InverterDTO() {
    }

    public InverterDTO(String serial, String name, YieldTodayDTO yieldToday) {
        this.serial = serial;
        this.name = name;
        this.yieldToday = yieldToday;
    }

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

    public YieldTodayDTO getYieldToday() {
        return yieldToday;
    }

    public void setYieldToday(YieldTodayDTO yieldToday) {
        this.yieldToday = yieldToday;
    }

    @Override
    public String toString() {
        return "InverterDTO{" +
                "serial='" + serial + '\'' +
                ", name='" + name + '\'' +
                ", yieldToday=" + yieldToday +
                '}';
    }
}
