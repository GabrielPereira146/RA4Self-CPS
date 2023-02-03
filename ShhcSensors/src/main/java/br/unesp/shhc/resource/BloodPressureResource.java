/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unesp.shhc.resource;
import br.unesp.shhc.model.BloodPressure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shhc/BloodPressure")
public class BloodPressureResource {
    private static final BloodPressure b;
    static {
       b = new BloodPressure(10, 6, "Normal", "Pressao");
    }

    @GetMapping("/")
    public BloodPressure getBloodPressure() {
        return b;
    }
    
    @RequestMapping(path = "/save", method = RequestMethod.GET)
    public void saveSensors(@RequestParam int diastolicValue,@RequestParam int  systolicValue) {
        
        BloodPressure b = new BloodPressure(diastolicValue, systolicValue);
        System.out.println("Sensores: " + b);

    }
}
