package com.second.second.sales;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalesRepository extends CrudRepository<Sales,Integer> {

    public Sales findByid(Integer id);

    public List<Sales> findAll();

    public List<Sales> findSalesByStatus(String status);

    @Query("SELECT e.sales from Employee e")
    public List<Sales> totalSales();

    @Query("SELECT new com.second.second.sales.salesByStatusDTO(s.status, COUNT(s)) FROM Sales s GROUP BY s.status")
    public List<salesByStatusDTO> salesStatus();

    @Query("SELECT new com.second.second.sales.totalSalesByStatus(s.status, SUM(s.sale)) FROM Sales s GROUP BY s.status")
    public List<totalSalesByStatus> salesStatusTotal();
}
