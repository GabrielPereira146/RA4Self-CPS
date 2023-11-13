package br.unesp.rc.shhc.SHHCModel.model;

import org.kie.api.runtime.KieSession;

public interface Analyzable {
    void setValue(int value);
    void applyRules(KieSession kSession);
    void setResult(String result);
    String getResult();
} 