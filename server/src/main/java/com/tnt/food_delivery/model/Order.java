package com.tnt.food_delivery.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import static com.tnt.food_delivery.model.Register.getCurrentTime;

@Data
@Builder(toBuilder = true)
@Document("order")
@Getter
@Setter
@AllArgsConstructor
public class Order {
    @Id
    private String id;

    @NonNull
    @DBRef
    private User userID;

    @Builder.Default
    private String createAt = getCurrentTime();

    @Builder.Default
    private String updateAt = getCurrentTime();

    @Builder.Default
    private List<Product> products = new ArrayList<>();

    @Builder.Default
    private PaymentMethod paymentMethod = PaymentMethod.CASH;

    @NonNull
    private String address;

    @NonNull
    private String wards;

    @NonNull
    private String district;

    @NonNull
    private String province;

    public enum PaymentMethod {
        CASH, PAYPAL,
    }
}
