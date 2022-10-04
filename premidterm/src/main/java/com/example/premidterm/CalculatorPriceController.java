package com.example.premidterm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorPriceController {
    @Autowired
    private CalculatorPriceService cal;

    @RequestMapping(value = "/getPrice/{cost}/{profit}", method = RequestMethod.GET)
    public int serviceGetProducts(@PathVariable("cost") int num1, @PathVariable("profit") int num2){
        return cal.getPrice(num1, num2);
    }
}
