package com.example.lab5_2;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import io.netty.util.internal.StringUtil;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route(value="index2")

public class myView02 extends FormLayout {
    public Word words = new Word();
    private TextField t1, t2, goodSen, badSen;
    private ComboBox<String> bad, good;
    private Button btn1, btn2, btn3, btn4;
    public myView02(){
        VerticalLayout col1 = new VerticalLayout();
        VerticalLayout col2 = new VerticalLayout();
        t1 = new TextField("Add Word");
        t2 = new TextField("Add Sentence");
        goodSen = new TextField("Good Sentence");
        badSen = new TextField("Bad Sentence");
        good = new ComboBox<>("Good Words");
        good.setItems(words.goodWords);
        bad = new ComboBox<>("Bad Words");
        bad.setItems(words.badWords);
        btn1 = new Button("Add Good Word");
        btn2 = new Button("Add Bad Word");
        btn3 = new Button("Add Sentence");
        btn4 = new Button("Show Sentence");
        t1.setWidth("510px");
        t2.setWidth("510px");
        goodSen.setWidth("510px");
        badSen.setWidth("510px");
        good.setWidth("510px");
        bad.setWidth("510px");
        btn1.setWidth("510px");
        btn2.setWidth("510px");
        btn3.setWidth("510px");
        btn4.setWidth("510px");
        col1.add(t1, btn1, btn2, good, bad);
        col2.add(t2, btn3, goodSen, badSen, btn4);
        add(col1, col2);
        btn1.addClickListener(e -> {
            ArrayList out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addgood/"+t1.getValue())
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            good.setItems(out);
            new Notification("Insert "+t1.getValue()+" to Good Word List Complete", 5000).open();
        });
        btn2.addClickListener(e -> {
            ArrayList out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/addbad/"+t1.getValue())
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            bad.setItems(out);
            new Notification("Insert "+t1.getValue()+" to Bad Word List Complete", 5000).open();
        });
        btn3.addClickListener(e -> {
            String out = WebClient.create()
                    .post()
                    .uri("http://localhost:8080/proof/"+t2.getValue())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            System.out.println("Proof Sentence Success");
            new Notification(out, 5000).open();
        });
        btn4.addClickListener(e -> {
            Sentence out = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getSentence")
                    .retrieve()
                    .bodyToMono(Sentence.class)
                    .block();
                goodSen.setValue(String.valueOf(out.goodSentences));
                badSen.setValue(String.valueOf(out.badSentences));

        });
    }
}
