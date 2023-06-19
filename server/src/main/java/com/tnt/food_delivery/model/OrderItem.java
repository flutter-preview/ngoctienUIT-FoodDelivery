package com.tnt.food_delivery.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

public class OrderItem {
    @DBRef
    private Product product;
    private Integer quantity;
//    private List<Topping> toppings;
    private String selectedSize;
}
