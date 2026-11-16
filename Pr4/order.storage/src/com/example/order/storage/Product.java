package com.example.order.storage;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class Product {
    private String name;
    private double price;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}