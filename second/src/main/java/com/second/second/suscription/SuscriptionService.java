package com.second.second.suscription;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class SuscriptionService {

    public SuscriptionService(SuscriptionRepository suscriptionRepository) {
        this.suscriptionRepository = suscriptionRepository;
    }

    private final SuscriptionRepository suscriptionRepository;

    public BigDecimal MRRCalc(){
        BigDecimal mrr = suscriptionRepository.calculateMRR();
        return mrr ;
    }

  public Double ChurnCalc(){
     return suscriptionRepository.calculateChurn();
  }

}
