package com.fanap.model;

public class Contact {
    private Integer id;
    private String name;

    public Contact(String name) {
        this.name = name;
    }

    public Contact() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "contact{ name :" + name + " id :" + id +" }";
    }
}
