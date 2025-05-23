package com.second.second.suscription;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/suscription")
public class SuscriptionController {

    private SuscriptionService suscriptionService;

    public SuscriptionController(SuscriptionService suscriptionService) {
        this.suscriptionService = suscriptionService;
    }

    @GetMapping("/mrrcalc")
    public BigDecimal MRRCalc(){
        return suscriptionService.MRRCalc();
    }

    @GetMapping("/customerchurn")
    public Double customerChurn(){
        return suscriptionService.customerChurnCalc();
    }

    @GetMapping("/revenuechurn")
    public Double revenueCalc(){
        return suscriptionService.revenueChurnCalc();
    }



}
