package com.second.second;

import com.github.javafaker.Faker;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
//    private Faker faker = new Faker();

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Integer totalSales(){
        var totalSales=employeeRepository.totalSales().size();

        return totalSales;
    }
//    List<Employee> people = IntStream.rangeClosed(1,100)
//            .mapToObj(i -> new Employee(
//                    faker.name().firstName() + "." +faker.name().lastName() + "@email.com",
//                    faker.job().position(),
//                    faker.name().firstName(),
//                    faker.name().lastName()
//                    )
//            ).collect(Collectors.toList());

//    public void saveAll(){
//        employeeRepository.saveAll(people);
//    }





}
