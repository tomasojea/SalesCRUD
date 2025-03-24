package com.second.second.billing;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface BillingRepository extends CrudRepository<Billing,Integer> {
    @Query("""
            """)
    BigDecimal calculateARR();
}
