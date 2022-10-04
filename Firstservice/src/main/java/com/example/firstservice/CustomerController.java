package com.example.firstservice;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
public class CustomerController {
    private List<Customer> customers = new ArrayList<Customer>(){{
        add(new Customer("1010","John","Male",25));
        add(new Customer("1018","Peter","Male",24));
        add(new Customer("1019","Sara","Female",23));
        add(new Customer("1110","Rose","Female",23));
        add(new Customer("1001","Emma","Female",30));
    }};

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public  List<Customer> getCustomers(){
        return customers;
    }

    @RequestMapping(value = "/customerbyid/{id}", method = RequestMethod.GET)
    public Customer getCustomerByID(@PathVariable("id") String id){
        int i = 0;
        for (; i<= customers.size(); i++) {
            if(customers.get(i).getId().equals(id)){
                break;
            }
        }
        return customers.get(i);
    }

    @RequestMapping(value = "/customerbyname/{name}", method = RequestMethod.GET)
    public Customer getCustomerByName(@PathVariable("name") String name){
        int i = 0;
        for (; i<= customers.size(); i++) {
            if(customers.get(i).getName().equals(name)){
                break;
            }
        }
        return customers.get(i);
    }

    @RequestMapping(value = "/delcustomerbyid/{id}", method = RequestMethod.DELETE)
    public boolean delCustomerByID(@PathVariable("id") String id){
        int i = 0;
        for (; i<= customers.size(); i++) {
            if(customers.get(i).getId().equals(id)){
                break;
            }
        }
        customers.remove(i);
        return true;
    }

    @RequestMapping(value = "/delcustomerbyname/{name}", method = RequestMethod.DELETE)
    public boolean delCustomerByName(@PathVariable("name") String name){
        int i = 0;
        for (; i<= customers.size(); i++) {
            if(customers.get(i).getName().equals(name)){
                break;
            }
        }
        customers.remove(i);
        return true;
    }

    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    public boolean addCustomer(@RequestParam("ID") String ID,@RequestParam("name") String n,@RequestParam("sex") String s,
                           @RequestParam("age") int a){
        this.customers.add(new Customer(ID, n, s, a));
        return true;
}

    @RequestMapping(value = "/addCustomer2", method = RequestMethod.POST)
    public boolean addCustomer2(@RequestParam("ID") String ID,@RequestParam("name") String n,@RequestParam("sex") String s,
                                @RequestParam("age") int a){
        this.customers.add(new Customer(ID, n, s, a));
        return true;
    }
}
