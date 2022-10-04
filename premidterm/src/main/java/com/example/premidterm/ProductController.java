package com.example.premidterm;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping(value = "/Product")
    @RabbitListener(queues = "GetAllProductQueue")
    public List<Product> serviceGetAllProduct(){
        return service.getAllProduct();
    }

    @GetMapping(value = "/nameProduct")
    @RabbitListener(queues = "GetNameProductQueue")
    public Product serviceGetProductByName(String productName){
        return service.getProductByName(productName);
    }

    @GetMapping(value = "/addProduct")
    @RabbitListener(queues = "AddProductQueue")
    public Boolean serviceAddProduct(Product product){
        return service.addProduct(product);
    }
    @GetMapping(value = "/updateProduct")
    @RabbitListener(queues = "UpdateProductQueue")
    public Boolean serviceUpdateProduct(Product product){
        return service.updateProduct(product);
    }

    @GetMapping(value = "/delProduct")
    @RabbitListener(queues = "DeleteProductQueue")
    public Boolean serviceDeleteProduct(Product product){
        return service.deleteProduct(product);
    }
}
