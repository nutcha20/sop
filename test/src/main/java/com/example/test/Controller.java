package com.example.test;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private service s;

    @RabbitListener(queues = "addData")
    public boolean checkAddSevice(Demo d){
        return s.addSevice(d);
    }
}
