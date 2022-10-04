package com.example.change;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Cashier {
        @RequestMapping(value ="getChange/{n1}", method = RequestMethod.GET)
        public Change getChange(@PathVariable("n1") int money){
            Change change_obj = new Change();
            change_obj.setB1000(money/1000);
            money %= 1000;
            change_obj.setB500(money/500);
            money %= 500;
            change_obj.setB100(money/100);
            money %= 100;
            change_obj.setB20(money/20);
            money %= 20;
            change_obj.setB10(money/10);
            money %= 10;
            change_obj.setB5(money/5);
            money %= 5;
            change_obj.setB1(money/1);
            return change_obj;

    }
}
