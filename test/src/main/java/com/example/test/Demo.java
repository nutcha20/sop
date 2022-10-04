package com.example.test;

import com.vaadin.flow.component.template.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Document("test")
public class Demo implements Serializable {
    @Id
    private String _id;
    private String name;
    private int age;

    public Demo(){}

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Demo(String _id, String name, int age) {
        this._id = _id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
