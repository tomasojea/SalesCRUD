package com.second.second;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SalesRepository extends CrudRepository<Sales,Integer> {

    public Sales findByid(Integer id);

    public List<Sales> findAll();

    public List<Sales> findSalesByStatus(String status);
}
