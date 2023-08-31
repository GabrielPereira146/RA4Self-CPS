package br.unesp.rc.shhc.SHHCSensorAPI.resources;

import br.unesp.rc.shhc.SHHCModel.model.AirFlow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shhc/AirFlow")
public class AirFlowResource {

    private static AirFlow air;

    static {
        air = new AirFlow(38, "Online", "1");
    }

    @GetMapping("/")
    public AirFlow getAirFlow() {
        this.print();
        return air;
    }

    @PutMapping("/update")
    public void updateTemperature(@RequestBody AirFlow airFlow) {
        System.out.println("Value to update: " + airFlow);
        air = airFlow;
        this.print();
    }

    @PostMapping("/save")
    public void save(@RequestBody AirFlow airFlow) {
        System.out.println("Value to save: " + airFlow);
        air = airFlow;
        this.print();
    }

    private void print() {
        System.out.println("------------------------------------------");
        System.out.println(air);
        System.out.println("------------------------------------------");
    }
}
