package br.unesp.rc.shhc.resource;

import br.unesp.rc.shhc.BloodPressure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shhc/BloodPressure")
public class BloodPressureResource {

    private static BloodPressure BloodP;

    static {
        BloodP = new BloodPressure(8, 12, "online", "3");
    }

    @GetMapping("/")
    public BloodPressure getBloodPressure() {
        this.print();
        return BloodP;
    }

    @PutMapping("/update")
    public void updateTemperature(@RequestBody BloodPressure bloodPressure) {
        System.out.println("Value to update: " + bloodPressure);
        BloodP = bloodPressure;        
        this.print();
    }

    @PostMapping("/save")
    public void save(@RequestBody BloodPressure bloodPressure) {
        System.out.println("Value to save: " + bloodPressure);                   
        BloodP = bloodPressure;        
        this.print();
    }

    private void print(){
        System.out.println("------------------------------------------");
        System.out.println(BloodP);
        System.out.println("------------------------------------------");
    }
}
