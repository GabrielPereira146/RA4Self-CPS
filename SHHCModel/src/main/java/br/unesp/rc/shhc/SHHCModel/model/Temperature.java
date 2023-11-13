package br.unesp.rc.shhc.SHHCModel.model;

import org.kie.api.runtime.KieSession;

public class Temperature implements Analyzable {

    // Attributes:
    private int value;
    private String status;
    private String ID;
    private String clazz;
    private int idClazz;

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
    public String getClazz() {
        return clazz;
    }


    @Override
    public String toString() {
        return "Temperature{" + "value=" + value + ", status=" + status + ", ID=" + ID + ", clazz=" + clazz + '}';
    }

    @Override
    public void applyRules(KieSession kSession) {
        kSession.insert(this);
        kSession.fireAllRules();
    }

    @Override
    public void setClazz(String clazz, int id) {
       this.clazz = clazz;
       this.idClazz = id;
    }

    @Override
    public int getIdClazz() {
       return idClazz;
    }

}
