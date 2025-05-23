package com.second.second.employee;

import com.second.second.sales.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
//    private Faker faker = new Faker();

    public EmployeeService(EmployeeRepository employeeRepository, KafkaTemplate<String, String> kafkaTemplate) {
        this.employeeRepository = employeeRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public String salesStatus(int id){
        return employeeRepository.findById(id).getSales().getFirst().getStatus();
    }

    public List<Sales> salesPerEmployee(int id){
        return employeeRepository.findById(id).getSales();
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public List<Employee> findAll(Sort sort){
        return employeeRepository.findAll(sort);
    }
    public Page<Employee> findAll(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public void deleteById(Integer id){
         employeeRepository.deleteById(id);
    }

    public List<Employee> findByName(String name){
        return employeeRepository.findByName(name);
    }

    public void send(String topicName, String value) {
        try {
            kafkaTemplate.send(topicName, value);
            System.out.println("Message sent to topic: " + topicName);
        } catch (Exception e) {
            System.err.println("Error sending message: " + e.getMessage());
        }

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
