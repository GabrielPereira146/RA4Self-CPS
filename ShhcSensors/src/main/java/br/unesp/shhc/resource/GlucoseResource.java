/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unesp.shhc.resource;
import br.unesp.shhc.model.Glucose;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shhc/Glucose")
public class GlucoseResource {
    private static final Glucose g;
    static {
       g = new Glucose(80, "Normal", "Glicose");
    }

    @GetMapping("/")
    public Glucose getGlucose() {
        return g;
    }
    
    @RequestMapping(path = "/save", method = RequestMethod.GET)
    public void saveSensors(@RequestParam int value) {
        
        Glucose g = new Glucose(value);
        System.out.println("Sensores: " + g);

    }
}
