/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo3.resource;
import com.example.demo3.model.PulseOxygen;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shhc/PulseOxygen")
public class PulseOxygenResource {
    private static final PulseOxygen p;
    static {
       p = new PulseOxygen(94, "Normal", "Oxigenio");
    }

    @GetMapping("/")
    public PulseOxygen getPulseOxygen() {
        return p;
    }
    
    @RequestMapping(path = "/save", method = RequestMethod.GET)
    public void saveSensors(@RequestParam int value) {
        
        PulseOxygen p = new PulseOxygen(value);
        System.out.println("Sensores: " + p);

    }
}
