package com.example.firstservice;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestRestAPI {
    @RequestMapping(value = "/add/{num1}/{num2}", method = RequestMethod.GET)
    public String add_num(@PathVariable("num1") double num1, @PathVariable("num2") double num2){
        return String.valueOf(num1+num2);
    }
    @RequestMapping(value = "/minus/{num1}/{num2}", method = RequestMethod.GET)
    public String minus_num(@PathVariable("num1") double num1, @PathVariable("num2") double num2){
        return String.valueOf(num1-num2);
    }
    @RequestMapping(value = "/multiply", method = RequestMethod.GET)
    public String multiply_num(@RequestParam("num1") double num1, @RequestParam("num2") double num2){
        return String.valueOf(num1*num2);
    }
    @RequestMapping(value = "/divide", method = RequestMethod.GET)
    public String divide_num(@RequestParam("num1") double num1, @RequestParam("num2") double num2){
        return String.valueOf(num1/num2);
    }
}
