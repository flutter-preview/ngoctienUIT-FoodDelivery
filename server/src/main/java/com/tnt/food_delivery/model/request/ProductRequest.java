package com.tnt.food_delivery.model.request;

import lombok.*;

@Getter
@Setter
public class ProductRequest {
    @NonNull
    private String restaurantID;

    @NonNull
    private String name;

    @NonNull
    private String image;

    @NonNull
    private String description;

    @Builder.Default
    private Boolean isSize = false;

    private Long price;
    private Long s;
    private Long m;
    private Long l;
}
