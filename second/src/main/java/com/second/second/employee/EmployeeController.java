package com.second.second.employee;

import com.second.second.sales.Sales;
import com.second.second.utils.MultipleSorts;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    private final EmployeeService employeeService;

    private Sort.Direction getSortDirection(String direction) {
        if (direction.equals("asc")) {
            return Sort.Direction.ASC;
        } else if (direction.equals("desc")) {
            return Sort.Direction.DESC;
        }

        return Sort.Direction.ASC;
    }

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
    @CrossOrigin
    @GetMapping("/allemployees")
    public List<Employee> allEmployess(){
        //employeeService.send("quickstart-events","all employees");

        return employeeService.findAll();
    }
    @CrossOrigin
    @GetMapping("/sortemployees")
    public ResponseEntity<List<Employee>>  sortEmployees(@RequestParam("sort") String[] sort){
        System.out.println("Sort: " + sort[0]);
        try {
            List<Sort.Order> orders = new ArrayList<Sort.Order>();
            System.out.println("orders: " + orders);
            System.out.println(sort[0].contains("."));
            if (sort[0].contains(".")) {
                // will sort more than 2 columns
                for (String sortOrder : sort) {
                    // sortOrder="column, direction"
                    String[] _sort = sortOrder.split("\\.");
                    System.out.println(_sort[0] +" "+ _sort[1]);
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
                System.out.println(" get first orders: " + orders.getFirst());
            } else {
                // sort=[column, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }
            System.out.println("Sort orders: " + orders.getFirst());
            List<Employee> employees = employeeService.findAll(Sort.by(orders));

            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            System.out.println("Sort orders: " + orders);
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
