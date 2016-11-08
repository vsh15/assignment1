package com.assignment.assignment1;


import java.io.Serializable;

public class MyList implements Serializable {

    private  String title;
    private  String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
