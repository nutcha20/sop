package com.example.premidterm;

import org.springframework.stereotype.Service;

@Service
public class CalculatorPriceService {
    public int getPrice(int num1, int num2){
        return num1+num2;
    }
}
