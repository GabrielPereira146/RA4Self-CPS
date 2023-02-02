/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo3.resource;
import com.example.demo3.model.HeartRate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shhc/HeartRate")
public class HeartRateResource {
    private static final HeartRate h;
    static {
       h = new HeartRate(60, "Normal", "Batimentos");
    }

    @GetMapping("/")
    public HeartRate getHeartRate() {
        return h;
    }
    
    @RequestMapping(path = "/save", method = RequestMethod.GET)
    public void saveSensors(@RequestParam int value) {
        
        HeartRate h = new HeartRate(value);
        System.out.println("Sensores: " + h);

    }
}
