package io.strubel.DTUstatistic.dto;

public class YieldTodayDTO {
    private int v;
    private String u;
    private int d;

    public YieldTodayDTO() {
    }

    public YieldTodayDTO(int v, String u, int d) {
        this.v = v;
        this.u = u;
        this.d = d;
    }

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

    @Override
    public String toString() {
        return "YieldTodayDTO{" +
                "v=" + v +
                ", u='" + u + '\'' +
                ", d=" + d +
                '}';
    }
}
