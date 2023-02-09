/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.unesp.rc.sshc.resource;

import br.unesp.rc.shhc.model.HeartRate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("shhc/HeartRate")
public class HeartRateResource {
    private static HeartRate hr;
    
    static{
        hr = new HeartRate(120,"online","1");
    }
    
    @GetMapping("/")
    public HeartRate getHeartRate(){
        this.print();
        return hr;
    }
    
    @PutMapping("/update")
    public void updateHeartRate(@RequestBody HeartRate heartrate) {
        System.out.println("Value to update: " + heartrate);
        this.hr = heartrate;        
        this.print();
    }
    
    @PostMapping("/save")
    public void save(@RequestBody HeartRate heartrate) {
        System.out.println("Value to save: " + heartrate);                   
        this.hr = heartrate;        
        this.print();
    }
    
    private void print(){
        System.out.println("------------------------------------------");
        System.out.println(this.hr);
        System.out.println("------------------------------------------");
    }
}
