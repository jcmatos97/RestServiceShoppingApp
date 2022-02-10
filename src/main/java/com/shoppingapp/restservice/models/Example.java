package com.shoppingapp.restservice.models;

public class Example {
    private final Long id;
    private final String text;

    public Example(Long id, String text) {
        this.id=id;
        this.text=text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}