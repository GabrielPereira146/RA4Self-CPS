package br.unesp.rc.shhc.SHHCSensorAPI.resources;

import br.unesp.rc.shhc.SHHCModel.model.Temperature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shhc/Temperature")
public class TemperatureResource {

    private static Temperature temp;

    static {
        temp = new Temperature(36, "Offline", "1");
    }

    @GetMapping("/")
    public Temperature getTemperature() {
        this.print();
        return temp;
    }

    @PutMapping("/update")
    public void updateTemperature(@RequestBody Temperature temperature) {
        System.out.println("Value to update: " + temperature);
        temp = temperature;
        this.print();
    }

    @PostMapping("/save")
    public void saveTemperature(@RequestBody Temperature temperature) {
        System.out.println("Value to save: " + temperature);
        temp = temperature;
        this.print();
    }

    private void print() {
        System.out.println("------------------------------------------");
        System.out.println(temp);
        System.out.println("------------------------------------------");
    }
}
