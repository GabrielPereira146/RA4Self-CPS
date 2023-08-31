package br.unesp.rc.shhc.SHHCModel.model;

public class AirFlow {

    private int value;
    private String status;
    private String ID;

    public AirFlow(int airflow, String status, String ID) {
        this.value = airflow;
        this.status = status;
        this.ID = ID;
    }

    public AirFlow(int value, String status) {
        this.value = value;
        this.status = status;
    }

    public AirFlow(int value) {
        this.value = value;
    }

    public AirFlow() {

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
