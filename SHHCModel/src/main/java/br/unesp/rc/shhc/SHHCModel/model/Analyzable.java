package br.unesp.rc.shhc.SHHCModel.model;

import org.kie.api.runtime.KieSession;

public interface Analyzable {
    void applyRules(KieSession kSession);
    String getClazz();
    int getIdClazz();
    void setClazz(String clazz, int id);
} 