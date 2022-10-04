package com.example.demo;

import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;

@Route(value = "index")
public class View extends HorizontalLayout {
    public View() {
        Checkbox checkbox = new Checkbox();
        checkbox.setLabel("Option");
        checkbox.setValue(true);
        add(checkbox);
    }
}

