package com.tnt.food_delivery.model;

import com.mongodb.lang.NonNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder(toBuilder = true)
@Document("user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;

    @Builder.Default
    private Boolean isMale = true;
    private String birthOfDate;

    @NonNull
    @Indexed(unique=true)
    private String username;

    @NonNull
    @Indexed(unique=true)
    private String email;

    @NonNull
    @Indexed(unique=true)
    private String phoneNumber;

    @NonNull
    private String password;

    private String address;
    private String wards;
    private String district;
    private String province;

    @Builder.Default
    private UserRole userRole = UserRole.USER;
    public enum UserRole {
        NO_ROLE,
        USER,
        DELIVER,
        RESTAURANT,
        ADMIN
    }
}