package com.abs.domain;

/**
 * Created by Declan on 07/04/2015.
 */
public class Ward {

    Integer id;
    String name;

    public Ward(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
