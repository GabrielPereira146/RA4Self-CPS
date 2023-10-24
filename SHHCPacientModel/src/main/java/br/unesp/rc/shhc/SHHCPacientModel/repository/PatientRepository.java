package br.unesp.rc.shhc.SHHCPacientModel.repository;

import java.util.ArrayList;
import java.util.List;
import java.net.Socket;

import javax.swing.JOptionPane;

import br.unesp.rc.shhc.SHHCPacientModel.model.Patient;

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
        
        int porta = 8080;
        do{
            porta++;
        }while(portChecker(porta) == false);
        
        // -------------------------------------------
        // Consultado se o paciente existe
        // -------------------------------------------
        for (Patient p : patients) {
            if (p.getFirstName().equals(patient.getFirstName()) && p.getLastName().equals(patient.getLastName())) {
                JOptionPane.showMessageDialog(null, "Paciente já cadastrado no Sistema");
                return false;
            }
        }

        patient.setPort(Integer.toString(porta));
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

    public static boolean portChecker(int port) {
        try {
            // Tenta criar um socket na porta especificada
            Socket socket = new Socket("localhost", port);

            // Se o socket for criado com sucesso, a porta está em uso
            socket.close();
            return false;
        } catch (Exception e) {
            // Se ocorrer uma exceção, a porta está disponível
            return true;
        }
    }

}
