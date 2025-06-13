package io.strubel.DTUstatistic.dto;

public class InverterSummary {
    private String name;
    private String serial;
    private int totalYield;

    public InverterSummary(String name, String serial, int totalYield) {
        this.name = name;
        this.serial = serial;
        this.totalYield = totalYield;
    }

    public void addYield(int yield) {
        this.totalYield += yield;
        System.out.println(totalYield);
        System.out.println(yield);
    }

    public String getName() {
        return name;
    }

    public String getSerial() {
        return serial;
    }

    public int getTotalYield() {
        return totalYield;
    }

    @Override
    public String toString() {
        return "InverterSummary{" +
                "name='" + name + '\'' +
                ", serial='" + serial + '\'' +
                ", totalYield=" + totalYield +
                '}';
    }
}
