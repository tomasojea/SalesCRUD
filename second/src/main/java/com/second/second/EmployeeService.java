package com.second.second;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
//    private Faker faker = new Faker();

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Integer totalSales(){
        return employeeRepository.totalSales().size();
    }

    public String salesStatus(int id){
        return employeeRepository.findById(id).getSales().getFirst().getStatus();
    }

    public List<salesByStatusDTO> salesByStatus(){
        return employeeRepository.salesStatus();
    }

    public List<totalSalesByStatus> salesByStatusTotal(){
        return employeeRepository.salesStatusTotal();
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
