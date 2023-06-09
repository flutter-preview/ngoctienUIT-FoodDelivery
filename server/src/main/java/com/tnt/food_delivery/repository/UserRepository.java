package com.tnt.food_delivery.repository;


import com.tnt.food_delivery.model.User;
import com.tnt.food_delivery.model.request.AuthenticationRequestEntity;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{name: {$regex: ?0 }}")
    List<User> findItemByName(String name);

    @Query("{'$or':[ {'username': ?0 , 'password': ?1}, {'email': ?0 , 'password': ?1}, {'phoneNumber': ?0 , 'password': ?1} ] }")
    User login(String username, String password);
}
