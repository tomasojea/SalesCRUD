package com.second.second;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.web.bind.annotation.*;

import java.io.StringWriter;
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

    @GetMapping("/template")
    public String htmlTemplate(){
        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile("C:/Users/Owner/Downloads/second/src/main/resources/template.html");

        StringWriter writer = new StringWriter();
        mustache.execute(writer, employeeService.salesByStatusTotal());
        String html = writer.toString();
        return html;

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

    @GetMapping("/allsaleswithstatus/{status}")
    public List<Sales> allSalesWithStatus(@PathVariable String status){
        return salesRepository.findSalesByStatus(status);
    }


}
