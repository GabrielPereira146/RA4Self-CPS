package br.unesp.rc.shhc.SHHCModel.model;

public class PulseOxygen {

    private int value;
    private String status;
    private String ID;
    private String clazz;

    public PulseOxygen(int value, String status, String ID) {
        this.value = value;
        this.status = status;
        this.ID = ID;
    }

    public PulseOxygen(int value, String status) {
        this.value = value;
        this.status = status;
    }

    public PulseOxygen(int value) {
        this.value = value;
    }

    public PulseOxygen() {

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

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "PulseOxygen{" + "value=" + value + ", status=" + status + ", ID=" + ID + ", clazz=" + clazz + '}';
    }

}
