package com.second.second;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EmployeeController {

    private EmployeeRepository employeeRepository;
    private EmployeeService employeeService;
    private SalesRepository salesRepository;

    public EmployeeController(EmployeeRepository employeeRepository, EmployeeService employeeService, SalesRepository salesRepository){
        this.employeeRepository = employeeRepository;
        this.employeeService = employeeService;
        this.salesRepository = salesRepository;
    }


    @GetMapping("/allemployees")
    public List<Employee> allEmployess(){
        return employeeRepository.findAll();
    }

    @GetMapping("/allemployeeswith/{name}")
    public List<Employee> allEmployeesWith(@PathVariable String name){
        return employeeRepository.findByName(name);
    }

    @GetMapping("/salewith/{id}")
    public Sales allSalesWith(@PathVariable Integer id){
        return salesRepository.findByid(id);
    }

    @GetMapping("/allsales")
    public List<Sales> allSales(){
        return salesRepository.findAll();
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

    @GetMapping("/totalsales")
    public Integer totalSales(){
        return employeeService.totalSales();
    }

    @GetMapping("/salesperemployee/{id}")
    public String salesPerEmployee(@PathVariable int id){
        return employeeService.salesStatus(id);
    }

    @GetMapping("/salesbystatus")
    public List<salesByStatusDTO> salesPerstatus(){
        return employeeService.salesByStatus();
    }

    @GetMapping("/salesbystatustotal")
    public List<totalSalesByStatus> salesPerstatusTotal(){
        return employeeService.salesByStatusTotal();
    }






}
