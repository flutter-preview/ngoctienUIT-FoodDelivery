package com.tnt.food_delivery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import static com.tnt.food_delivery.model.Register.getCurrentTime;

@Data
@Builder(toBuilder = true)
@Document("product")
@Getter
@Setter
@AllArgsConstructor
public class Product {
    @Id
    private String id;

    @NonNull
    private String restaurantID;

    @NonNull
    private String name;

    @NonNull
    private String image;

    @NonNull
    private String description;

    @Builder.Default
    private ProductStatus status = ProductStatus.PENDING;

    @Builder.Default
    private Boolean isSize = false;

    @Builder.Default
    String createAt = getCurrentTime();

    @Builder.Default
    String updateAt = getCurrentTime();

    private Long price;
    private Long s;
    private Long m;
    private Long l;

    public enum ProductStatus {
        PENDING, LAUNCH, CANCEL, DELETE,
    }
}
