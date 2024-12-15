package com.second.second;

import com.github.javafaker.Faker;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private KafkaTemplate<String, String> kafkaTemplate;
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
        var future = kafkaTemplate.send(topicName, value);
        future.whenComplete((sendResult, exception) -> {
            if (exception != null) {
                future.completeExceptionally(exception);
            } else {
                future.complete(sendResult);
            }

        });
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
