/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo3.resource;
import com.example.demo3.model.Temperature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/shhc/Temperature")
public class TemperatureResource {
    private static Temperature temp;
    static {
       temp= new Temperature(36, "Normal", "Temperatura");
    }
   
   @GetMapping("/")
    public Temperature getTemperature(){
        return temp;
    }
    
    @PutMapping("/update")
    public void updateTemperature(@RequestBody Temperature temperature){
         System.out.println("Value to update: " + temperature);
        temp.setValue((int) temperature.getValue());
    }
    
    @PostMapping("/save")
    public void save(@RequestBody Temperature temperature) {
        System.out.println("Value to save: " + temperature);
        temp.setValue((int) temperature.getValue());
    }


}
