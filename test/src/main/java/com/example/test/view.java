package com.example.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;


@Route(value = "index")
public class view extends VerticalLayout{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private TextField name;
    private TextField age;
    private Button addd;
    public view(){
        name = new TextField("name");
        age = new TextField("age");
        addd = new Button("add");
        add(name, age, addd);
        addd.addClickListener(e -> {
           Demo form = new Demo(null, name.getValue(), Integer.parseInt(age.getValue()));
           Boolean check = (Boolean) rabbitTemplate.convertSendAndReceive("testExchange", "add", form);
           if(check){
               new Notification("Add Finish", 5000).open();
           }

        });
    }
}
