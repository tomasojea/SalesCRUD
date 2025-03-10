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

    @GetMapping("/calc")
    public BigDecimal MRRCalc(){
        return suscriptionService.MRRCalc();
    }



}
