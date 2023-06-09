package com.tnt.food_delivery.model.request;

import com.mongodb.lang.NonNull;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationRequestEntity {

//    @NonNull
    private String username;

//    @NonNull
    private String password;
}
