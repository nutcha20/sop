package com.example.change;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

//import java.awt.*;
@Route(value = "index2")
public class CashierView extends VerticalLayout {
    private TextField txt1;
    private Button btn;
    private TextField txt3;
    private TextField txt4;
    private TextField txt5;
    private TextField txt6;
    private TextField txt7;
    private TextField txt8;
    private TextField txt9;
    public CashierView(){
        txt1 = new TextField();
        txt1.setLabel("เงินทอน");
        btn = new Button("คำนวณเงินทอน");
        txt3 = new TextField();
        txt3.setPrefixComponent(new Span("$1000"));
        txt4 = new TextField();
        txt4.setPrefixComponent(new Span("$500"));
        txt5 = new TextField();
        txt5.setPrefixComponent(new Span("$100"));
        txt6 = new TextField();
        txt6.setPrefixComponent(new Span("$20"));
        txt7 = new TextField();
        txt7.setPrefixComponent(new Span("$10"));
        txt8 = new TextField();
        txt8.setPrefixComponent(new Span("$5"));
        txt9 = new TextField();
        txt9.setPrefixComponent(new Span("$1"));
        add(txt1, btn, txt3, txt4, txt5, txt6, txt7, txt8, txt9);
        btn.addClickListener(event-> {
            int num1 = Integer.parseInt(txt1.getValue());
            Change out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/getChange/"+num1)
                    .retrieve()
                    .bodyToMono(Change.class)
                    .block();
            txt3.setValue(String.valueOf(out.getB1000()));
            txt4.setValue(String.valueOf(out.getB500()));
            txt5.setValue(String.valueOf(out.getB100()));
            txt6.setValue(String.valueOf(out.getB20()));
            txt7.setValue(String.valueOf(out.getB10()));
            txt8.setValue(String.valueOf(out.getB5()));
            txt9.setValue(String.valueOf(out.getB1()));

        });
    }

}
