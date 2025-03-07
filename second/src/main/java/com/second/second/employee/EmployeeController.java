package com.second.second.employee;

import com.second.second.sales.Sales;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
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
        return employeeService.findAll();
    }

    @GetMapping("/allemployeeswith/{name}")
    public List<Employee> allEmployeesWith(@PathVariable String name){
        return employeeService.findByName(name);
    }

    @PostMapping("/addemployee")
    public Employee addEmployee(Employee employee){
        return employeeService.save(employee);
    }

    @PostMapping("/deleteemployee")
    public String deleteEmployee(Integer id){
         employeeService.deleteById(id);
         return id + " was deleted";
    }

    @GetMapping("/salesperemployee/{id}")
    public List<Sales> salesPerEmployee(@PathVariable int id){
        return employeeService.salesPerEmployee(id);
    }

}
