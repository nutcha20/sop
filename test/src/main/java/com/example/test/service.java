package com.example.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class service {
    @Autowired
    private repo r;

    public boolean addSevice(Demo d){
        try {
            r.save(d);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
