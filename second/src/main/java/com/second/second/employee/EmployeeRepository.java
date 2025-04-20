package com.second.second.employee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface EmployeeRepository extends CrudRepository<Employee,Integer>
{

    public List<Employee> findAll();

    public List<Employee> findAll(Sort sort);

    public Page<Employee> findAll(Pageable pageable);

    public List<Employee> findByName(String name);

    public Employee findById(int id);

}
