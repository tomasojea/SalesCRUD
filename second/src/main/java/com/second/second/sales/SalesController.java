package com.second.second.sales;


import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.StringWriter;
import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {

    public SalesController(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    private SalesRepository salesRepository;

    @GetMapping("/template")
    public String htmlTemplate(){

        return TemplateHelper.getTemplate(salesRepository.salesStatusTotal());

    }

    @GetMapping("/salewith/{id}")
    public Sales allSalesWith(@PathVariable Integer id){
        return salesRepository.findByid(id);
    }

    @GetMapping("/allsales")
    public List<Sales> allSales(){
        return salesRepository.findAll();
    }

    @GetMapping("/totalsales")
    public Integer totalSales(){
        return salesRepository.totalSales().size();
    }

    @GetMapping("/salesbystatus")
    public List<salesByStatusDTO> salesPerstatus(){
        return salesRepository.salesStatus();
    }

    @GetMapping("/salesbystatustotal")
    public List<totalSalesByStatus> salesPerstatusTotal(){
        return salesRepository.salesStatusTotal();
    }

    @GetMapping("/allsaleswithstatus/{status}")
    public List<Sales> allSalesWithStatus(@PathVariable String status){
        return salesRepository.findSalesByStatus(status);
    }

}
