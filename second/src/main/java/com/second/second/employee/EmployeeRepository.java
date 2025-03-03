package com.second.second.employee;


import com.second.second.sales.Sales;
import com.second.second.sales.salesByStatusDTO;
import com.second.second.sales.totalSalesByStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee,Integer>
{

    public List<Employee> findAll();

    public List<Employee> findByName(String name);

    public Employee findById(int id);

}
