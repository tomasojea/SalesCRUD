package com.second.second;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


interface EmployeeRepository extends CrudRepository<Employee,Integer>
{

    public List<Employee> findAll();

    public List<Employee> findByName(String name);


}
