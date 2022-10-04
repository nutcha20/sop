package com.example.firstservice;
import java.lang.Math;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeneratePasswordService {
    @RequestMapping(path = "{name:[A-z]+}.generate", method = RequestMethod.GET)
    public String password(@PathVariable("name") String name){
        int pw = (int)((Math.random()) * Math.pow(10, 9));
        return "Hi, " + name + "\n"+"Your new password is " + pw;
    }
}
