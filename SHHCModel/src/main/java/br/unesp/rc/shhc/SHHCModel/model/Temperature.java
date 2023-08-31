package br.unesp.rc.shhc.SHHCModel.model;

public class Temperature {

    //Attributes:
    private int value;
    private String status;
    private String ID;

    public Temperature() {
    }

    public Temperature(int value, String status, String ID) {
        this.value = value;
        this.status = status;
        System.out.println("ID" + ID);
        this.ID = ID;
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
        return "Temperature{" + "value=" + this.value + ", status=" + this.status + ", ID=" + this.ID + '}';
    }

}
