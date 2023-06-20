package com.tnt.food_delivery.data.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class OrderItem {
    @DBRef
    private Product product;
    private Integer quantity;
//    private List<Topping> toppings;
    private String selectedSize;
}
