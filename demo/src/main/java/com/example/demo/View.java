package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@Route(value="index")
@RestController
public class View extends VerticalLayout {
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private TextField txt1;
    private TextField txt2;
    private TextField txt3;
    public View(){
        txt1 = new TextField("Number 1");
        txt2 = new TextField("Number 2");
        HorizontalLayout horizontal = new HorizontalLayout();
        btn1 = new Button("+");
        btn2 = new Button("-");
        btn3 = new Button("x");
        btn4 = new Button("/");
        btn5 = new Button("Mod");
        btn6 = new Button("Max");
        txt3 = new TextField("Answer");

        horizontal.add(btn1, btn2, btn3, btn4, btn5, btn6);
        add(txt1, txt2,horizontal, txt3);
        btn1.addClickListener(event-> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/plus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);
        });
        btn2.addClickListener(event-> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/minus/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);
        });
        btn4.addClickListener(event-> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/divide/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);
        });
        btn3.addClickListener(event-> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/multi/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);
        });
        btn5.addClickListener(event-> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/myMod/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);
        });
        btn6.addClickListener(event-> {
            double num1 = Double.parseDouble(txt1.getValue());
            double num2 = Double.parseDouble(txt2.getValue());

            String out = WebClient.create()
                    .post()
                    .uri("http://127.0.0.1:8080/myMax/"+num1+"/"+num2)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            txt3.setValue(out);
        });
    }

    @RequestMapping(value ="plus/{n1}/{n2}", method = RequestMethod.GET)
    public double plus(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return  num1+num2;
    }

    @RequestMapping(value ="minus/{n1}/{n2}", method = RequestMethod.GET)
    public double minus(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return  num1-num2;
    }

    @RequestMapping(value ="divide/{n1}/{n2}", method = RequestMethod.GET)
    public double divide(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return  num1/num2;
    }

    @RequestMapping(value ="multi/{n1}/{n2}", method = RequestMethod.GET)
    public double multi(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return  num1*num2;
    }

    @RequestMapping(value ="myMod/{n1}/{n2}", method = RequestMethod.GET)
    public double myMod(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
        return  num1%num2;
    }

    @RequestMapping(value ="myMax/{n1}/{n2}", method = RequestMethod.POST)
    public double myMax(@PathVariable("n1") double num1, @PathVariable("n2") double num2){
       if(num1 > num2){
           return num1;
       }
       else{
           return num2;
       }
    }

}

