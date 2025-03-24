package com.second.second.suscription;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;

public interface SuscriptionRepository extends CrudRepository<Subscriptions,Integer> {

    @Query(value = "SELECT total_mrr FROM mrr", nativeQuery = true)
    BigDecimal calculateMRR();
    @Query(value = "SELECT division_result FROM revenue_churn", nativeQuery = true)
    Double calculateRevenueChurn();
    @Query(value = "SELECT churn_rate FROM customer_churn", nativeQuery = true)
    Double calculateCustomerChurn();


}
