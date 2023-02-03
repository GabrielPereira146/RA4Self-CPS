/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unesp.shhc.resource;

import br.unesp.shhc.model.AirFlow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/shhc/AirFlow")
public class AirFlowResource {

    private static AirFlow a;
    
    static {
       a = new AirFlow(4, "Normal", "Air");
    }

    @GetMapping("/")
    public AirFlow getAirFlow() {
        return a;
    }
    
    @RequestMapping(path = "/save", method = RequestMethod.GET)
    public void saveSensors(@RequestParam int value) {
        
        AirFlow a = new AirFlow(value);
        System.out.println("Sensores: " + a);

    }
}
