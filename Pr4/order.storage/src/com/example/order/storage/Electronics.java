package com.example.order.storage;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Electronics extends Product {
    private String brand;
    private int warrantyMonths;
}