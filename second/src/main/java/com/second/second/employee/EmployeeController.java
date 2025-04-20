package com.second.second.employee;

import com.second.second.sales.Sales;
import com.second.second.utils.MultipleSorts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Map<String, Object>>  sortEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size,
            @RequestParam("sort") String[] sort){
        System.out.println("Sort: " + sort[0]);
        try {
            List<Sort.Order> orders = new ArrayList<>();
            if (sort[0].contains(".")) {
                // will sort more than 2 columns
                for (String sortOrder : sort) {
                    // sortOrder="column, direction"
                    String[] _sort = sortOrder.split("\\.");
                    orders.add(new Sort.Order(getSortDirection(_sort[1]), _sort[0]));
                }
            } else {
                // sort=[column, direction]
                orders.add(new Sort.Order(getSortDirection(sort[1]), sort[0]));
            }
            List<Employee> employees = new ArrayList<>();
            Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));
            Page<Employee> pageTuts;
            pageTuts = employeeService.findAll(pagingSort);
            employees =  pageTuts.getContent();
            if (employees.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("employees", employees);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
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
