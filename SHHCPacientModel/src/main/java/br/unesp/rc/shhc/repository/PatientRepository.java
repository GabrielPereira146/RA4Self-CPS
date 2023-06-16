package br.unesp.rc.shhc.repository;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.unesp.rc.shhc.model.Patient;

public class PatientRepository {
    public static List<Patient> patients = new ArrayList<>();

    private PatientRepository() {

    }

    public static Patient consultarPrimeiroNome(String nome) {
        Patient patient = null;

        for (Patient p : patients) {
            if (p.getFirstName().equals(nome)) {
                patient = p;
            }
        }

        return patient;
    }

    public static boolean cadastrarPaciente(Patient patient) {
        // -------------------------------------------
        // Consultado se o paciente existe
        // -------------------------------------------
        for (Patient p : patients) {
            if (p.getFirstName().equals(patient.getFirstName()) && p.getLastName().equals(patient.getLastName())) {
                JOptionPane.showMessageDialog(null, "Paciente já cadastrado no Sistema");
                return false;
            }
        }
        patients.add(patient);
        return true;
    }

    public static void listarTodos() {
        System.out.println("---");
        for (Patient p : patients) {
            System.out.println(p);
        }
        System.out.println("---");
    }
}
