package com.second.second.employee;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.second.second.sales.Sales;
import com.second.second.sales.SalesRepository;
import com.second.second.sales.salesByStatusDTO;
import com.second.second.sales.totalSalesByStatus;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService, SalesRepository salesRepository){
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
    }

    @GetMapping("/htmlpage")
    public String htmlPage(){
        return """
                <!DOCTYPE html>\
                <html>
                <head>
                <title>HTML CSS JS</title>
                </head>
                <body>
                <h1 id="welcome">HTML CSS JS</h1>
                <p>Welcome to HTML-CSS-JS.com</p>
                <p>Online HTML, CSS and JavaScript editor\s
                with instant preview.</p>
                </body>
                </html>""";
    }

    @GetMapping("/allemployees")
    public List<Employee> allEmployess(){
        employeeService.send("quickstart-events","all employees");
        return employeeRepository.findAll();
    }

    @GetMapping("/allemployeeswith/{name}")
    public List<Employee> allEmployeesWith(@PathVariable String name){
        return employeeRepository.findByName(name);
    }

    @PostMapping("/addemployee")
    public Employee addEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    @PostMapping("/deleteemployee")
    public String deleteEmployee(Integer id){
         employeeRepository.deleteById(id);
         return id + " was deleted";
    }

    @GetMapping("/salesperemployee/{id}")
    public String salesPerEmployee(@PathVariable int id){
        return employeeService.salesStatus(id);
    }

}
