package br.unesp.shhc.model;

public class BloodPressure {

    private int diastolicValue;
    private int systolicValue;
    private String status;
    private String ID;

    public BloodPressure(int diastolicValue, int systolicValue, String status, String ID) {
        this.diastolicValue = diastolicValue;
        this.systolicValue = systolicValue;
        this.status = status;
        this.ID = ID;
    }

    public BloodPressure(int diastolicValue, int systolicValue, String status) {
        this.diastolicValue = diastolicValue;
        this.systolicValue = systolicValue;
        this.status = status;
    }

    public BloodPressure(int diastolicValue, int systolicValue) {
        this.diastolicValue = diastolicValue;
        this.systolicValue = systolicValue;
    }

    public BloodPressure() {

    }

    //Getter and setters:
    public int getDiastolicValue() {
        return diastolicValue;
    }

    public void setDiastolicValue(int diastolicValue) {
        this.diastolicValue = diastolicValue;
    }

    public int getSystolicValue() {
        return systolicValue;
    }

    public void setSystolicValue(int systolicValue) {
        this.systolicValue = systolicValue;
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
        return "diastolicValue = " + diastolicValue + ", systolicValue = " + systolicValue + ", status = " + status + ", ID = " + ID;
    }
}
