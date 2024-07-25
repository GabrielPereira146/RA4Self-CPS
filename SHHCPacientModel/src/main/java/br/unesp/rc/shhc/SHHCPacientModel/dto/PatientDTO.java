package br.unesp.rc.shhc.SHHCPacientModel.dto;

import lombok.Data;

@Data
public class PatientDTO{

    private int idPaciente;
    private String firstName;
    private String lastName;
    private float height;
    private float weight;
    private int age;
    private String port;
    
    public PatientDTO(){

    }

    public PatientDTO(int idPaciente, String firstName, String lastName, float height, float weight, int age,
            String port) {
        this.idPaciente = idPaciente;
        this.firstName = firstName;
        this.lastName = lastName;
        this.height = height;
        this.weight = weight;
        this.age = age;
        this.port = port;
    }

    
}
