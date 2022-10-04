package com.example.firstservice;

public class Customer {
    private String ID;
    private String name;
    private boolean sex;
    private int age;
    public Customer(){
        this("", null, "female", 0);
    }
    public Customer(String id, String n, String s, int a){
        this.ID = id;
        this.name = n;
        setSex(s);
        this.age = a;
    }
    public void setId(String id){
        this.ID = id;
    }
    public String getId(){
        return ID;
    }
    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }
    public void setSex(String s){
        if(s.equals("male") || s.equals("Male")){
            this.sex = true;
        } else if (s.equals("female") || s.equals("Female")) {
            this.sex = false;
        }
    }
    public boolean getSex(){
        return sex;
    }
    public void setAge(int a){
        if(a < 0){
            this.age = 0;
        }
        this.age = a;
    }
    public int getAge(){
        return age;
    }

}
