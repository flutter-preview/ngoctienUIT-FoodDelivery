package com.tnt.food_delivery.model.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProductRequest {
    private String name;
    private String image;
    private String description;
    private Boolean isSize;
    private Long price;
    private Long s;
    private Long m;
    private Long l;
}
