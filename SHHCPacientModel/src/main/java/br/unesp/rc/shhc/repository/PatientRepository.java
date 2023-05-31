package br.unesp.rc.shhc.repository;

import java.util.HashSet;
import java.util.Set;

import br.unesp.rc.shhc.model.Patient;

public class PatientRepository {
    public static Set<Patient> patients = new HashSet<>();

    private PatientRepository(){

    }
}
