package br.unesp.rc.shhc.SHHCSensorAPI.resources;

import br.unesp.rc.shhc.SHHCModel.model.Glucose;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shhc/Glucose")
public class GlucoseResource {

    private static Glucose glu;

    static {
        glu = new Glucose(100, "online", "1");
    }

    @GetMapping("/")
    public Glucose getGlucose() {
        this.print();
        return glu;
    }

    @PutMapping("/update")
    public void updateGlucose(@RequestBody Glucose glucose) {
        System.out.println("Value to update: " + glucose);
        glu = glucose;
        this.print();
    }

    @PostMapping("/save")
    public void save(@RequestBody Glucose glucose) {
        System.out.println("Value to save: " + glucose);
        glu = glucose;
        this.print();
    }

    private void print() {
        System.out.println("------------------------------------------");
        System.out.println(glu);
        System.out.println("------------------------------------------");
    }
}
