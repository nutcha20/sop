package com.example.premidterm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;
    public List<Product> getAllProduct(){
        try {
            return repository.findAll();
        } catch (Exception e) {
            return null;
        }
    }
    public Product getProductByName (String productName){
        try {
            return repository.findByName(productName);
        } catch (Exception e) {
            return null;
        }
    }
    public Boolean addProduct(Product pd){
        try {
            repository.save(pd);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public Boolean updateProduct(Product pd){
        try {
            repository.save(pd);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean deleteProduct(Product pd){
        try {
            repository.delete(pd);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
