package br.unesp.rc.shhc.AvailablePatient.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shhc")
public class NumberPatientResource {

    private static int numberPat;

    static {
        numberPat = 0;
    }

    @GetMapping("/")
    public int getNumberPat() {
        this.print();
        return numberPat;
    }

    @PutMapping("/update")
    public void updateNumberPatient(@RequestBody int numberPatients) {
        System.out.println("Value to update: " + numberPatients);
        numberPat = numberPatients;
        this.print();
    }

    @PostMapping("/save")
    public void saveNumberPatient(@RequestBody int numberPatients) {
        System.out.println("Value to save: " + numberPatients);
        numberPat = numberPatients;
        this.print();
    }

    private void print() {
        System.out.println("------------------------------------------");
        System.out.println(numberPat);
        System.out.println("------------------------------------------");
    }
}
