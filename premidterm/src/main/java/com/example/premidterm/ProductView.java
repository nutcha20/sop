package com.example.premidterm;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import com.vaadin.flow.component.notification.Notification;
import java.util.ArrayList;
import java.util.List;

@Route(value = "index")
public class ProductView extends VerticalLayout{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    private ComboBox<String> list;
    private TextField name;
    private TextField cost;
    private TextField profit;
    private TextField price;

    private Button add;
    private Button update;
    private Button del;
    private Button clear;

    public ProductView() {
        list = new ComboBox<>("Product List");
        name = new TextField("Product Name");
        cost = new TextField("Product Cost");
        profit = new TextField("Product Profit");
        price = new TextField("Product Price");

        list.setItems("");
        name.setValue("");
        cost.setValue("0");
        profit.setValue("0");
        price.setValue("0");

        list.setWidth("600px");
        name.setWidth("600px");
        profit.setWidth("600px");
        price.setWidth("600px");

        price.setEnabled(false);
        add = new Button("Add Product");
        update = new Button("Update Product");
        del = new Button("Delete Product");
        clear = new Button("Clear Product");

        HorizontalLayout row = new HorizontalLayout();
        row.add(add, update, del, clear);
        this.add(list, name, cost, profit, price, row);

        list.addFocusListener(event -> {
            List<Product> allProduct = (ArrayList<Product>) rabbitTemplate.convertSendAndReceive("ProductExchange", "getall", "");
            ArrayList<String> allProductName = new ArrayList<String>();
            for(Product p:allProduct) {
                allProductName.add(p.getProductName());
            }
            list.setItems(allProductName);
        });
        list.addValueChangeListener(event -> {
            String nameProduct = list.getValue();
            Product product = (Product) rabbitTemplate.convertSendAndReceive("ProductExchange", "getname", nameProduct);
            name.setValue(product.getProductName());
            cost.setValue(product.getProductCost()+"");
            profit.setValue(product.getProductProfit()+"");
            price.setValue(product.getProductPrice()+"");
        });
        add.addClickListener(event -> {
           int productPrice = WebClient.create()
                   .get()
                   .uri("http://localhost:8080/getPrice/" + cost.getValue() + "/" + profit.getValue())
                   .retrieve()
                   .bodyToMono(int.class)
                   .block();
           price.setValue(productPrice+"");
           Product p = new Product(null, name.getValue(), Integer.parseInt(cost.getValue()), Integer.parseInt(profit.getValue()), productPrice);
           Boolean addTure = (Boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "add", p);
           if(addTure){
               new Notification("Add Finish", 5000).open();
           }
        });

        update.addClickListener(event -> {
            int productPrice = WebClient.create()
                    .get()
                    .uri("http://localhost:8080/getPrice/" + cost.getValue() + "/" + profit.getValue())
                    .retrieve()
                    .bodyToMono(int.class)
                    .block();
            price.setValue(productPrice+"");
            Product productByName = (Product) rabbitTemplate.convertSendAndReceive("ProductExchange", "getname", list.getValue());
            Product p = new Product(productByName.get_id(), name.getValue(), Integer.parseInt(cost.getValue()), Integer.parseInt(profit.getValue()), productPrice);
            Boolean updateTure = (Boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "update", p);
            if(updateTure){
                new Notification("Update Finish", 5000).open();
            }
        });

        del.addClickListener(event -> {
            Product productByName = (Product) rabbitTemplate.convertSendAndReceive("ProductExchange", "getname", list.getValue());
            Boolean delTure = (Boolean) rabbitTemplate.convertSendAndReceive("ProductExchange", "delete", productByName);
            if(delTure){
                new Notification("Delete Finish", 5000).open();
            }
        });
        clear.addClickListener(buttonClickEvent -> {
            name.setValue("");
            cost.setValue("0");
            profit.setValue("0");
            price.setValue("0");
            new Notification("Clear Finish", 5000).open();
        });

    }




}
