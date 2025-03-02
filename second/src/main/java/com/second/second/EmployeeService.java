package com.second.second;

import com.github.javafaker.Faker;
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
