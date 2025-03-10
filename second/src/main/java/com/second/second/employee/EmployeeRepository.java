package com.second.second.employee;

import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee,Integer>
{

    public List<Employee> findAll();

    public List<Employee> findByName(String name);

    public Employee findById(int id);

}
