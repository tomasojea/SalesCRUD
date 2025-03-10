package com.second.second.suscription;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;

public interface SuscriptionRepository extends CrudRepository<Subscriptions,Integer> {

    @Query("""
    SELECT COALESCE(SUM(
        CASE 
            WHEN s.billing_cycle = 'monthly' THEN s.amount 
            WHEN s.billing_cycle = 'yearly' THEN s.amount / 12 
            ELSE 0 
        END
    ), 0) AS amount 
    FROM Subscriptions s WHERE s.status = 'active'
""")
    BigDecimal calculateMRR();

}
