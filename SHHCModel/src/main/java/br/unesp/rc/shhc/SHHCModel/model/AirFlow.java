package br.unesp.rc.shhc.SHHCModel.model;

import org.kie.api.runtime.KieSession;

public class AirFlow implements Analyzable {

    private int value;
    private String status;
    private String ID;
    private String clazz;
    private String result;

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

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "AirFlow{" + "value=" + value + ", status=" + status + ", ID=" + ID + ", clazz=" + clazz + '}';
    }

    @Override
    public void applyRules(KieSession kSession) {
        kSession.insert(this);
        kSession.fireAllRules();
    }

    @Override
    public String getResult() {
        return result;
    }

}
