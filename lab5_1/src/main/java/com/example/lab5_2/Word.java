package com.example.lab5_2;

import java.util.ArrayList;

public class Word {
    public ArrayList<String> goodWords = new ArrayList<String>();
    public ArrayList<String> badWords = new ArrayList<String>();
    public Word(){
        this.goodWords.add("happy");
        this.goodWords.add("enjoy");
        this.goodWords.add("like");
        this.badWords.add("fuck");
        this.badWords.add("olo");
    }
}
