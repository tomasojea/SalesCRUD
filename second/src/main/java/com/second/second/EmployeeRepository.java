package com.second.second;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface EmployeeRepository extends CrudRepository<Employee,Integer>
{

    public List<Employee> findAll();

    public List<Employee> findByName(String name);

    @Query("SELECT e.sales from Employee e")
    public List<Sales> totalSales();

    public Employee findById(int id);

    @Query("SELECT new com.second.second.salesByStatusDTO(s.status, COUNT(s)) FROM Sales s GROUP BY s.status")
    public List<salesByStatusDTO> salesStatus();

    @Query("SELECT new com.second.second.totalSalesByStatus(s.status, SUM(s.sale)) FROM Sales s GROUP BY s.status")
    public List<totalSalesByStatus> salesStatusTotal();


}
