package br.unesp.rc.sshc.resource;

import br.unesp.rc.shhc.model.PulseOxygen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shhc/PulseOxygen")
public class PulseOxygenResource {

    private static PulseOxygen oxyg;

    static {
        oxyg = new PulseOxygen(92, "Online", "1");
    }

    @GetMapping("/")
    public PulseOxygen getPulseOxygen() {
        this.print();
        return oxyg;
    }

    @PutMapping("/update")
    public void updateTemperature(@RequestBody PulseOxygen pulseOxygen) {
        System.out.println("Value to update: " + pulseOxygen);
        oxyg = pulseOxygen;        
        this.print();
    }

    @PostMapping("/save")
    public void save(@RequestBody PulseOxygen pulseOxygen) {
        System.out.println("Value to save: " + pulseOxygen);                   
        oxyg = pulseOxygen;        
        this.print();
    }

    private void print(){
        System.out.println("------------------------------------------");
        System.out.println(oxyg);
        System.out.println("------------------------------------------");
    }
}
