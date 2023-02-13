package br.unesp.rc.shhc.model;

public class HeartRate {

    private int value;
    private String status;
    private String ID;

    public HeartRate(int value, String status, String ID) {
        this.value = value;
        this.status = status;
        this.ID = ID;
    }

    public HeartRate(int value, String status) {
        this.value = value;
        this.status = status;
    }

    public HeartRate(int value) {
        this.value = value;
    }

    public HeartRate() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "value = " + value + ", status = " + status + ", ID = " + ID;
    }
}
