package br.unesp.rc.shhc.SHHCSensorAPI.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unesp.rc.shhc.SHHCPacientModel.dto.PatientDTO;

@RestController
@RequestMapping("/shhc/Patient")
public class PatientResource {

    public static PatientDTO patient;


    static {
        patient = new PatientDTO(0, "", "", 0, 0, 0, "");
    }
    @GetMapping("/")
    public PatientDTO getPatient() {
        this.print();
        return patient;
    }

    @PutMapping("/update")
    public void updatePatient(@RequestBody PatientDTO patientUpdate) {
        System.out.println("Value to update: " + patientUpdate);
        patient = patientUpdate;
        this.print();
    }

    @PostMapping("/save")
    public void save(@RequestBody PatientDTO newPatient) {
        System.out.println("Value to save: " + newPatient);
        patient = newPatient;
        this.print();
    }

    private void print() {
        System.out.println("------------------------------------------");
        System.out.println(patient);
        System.out.println("------------------------------------------");
    }
    
}
