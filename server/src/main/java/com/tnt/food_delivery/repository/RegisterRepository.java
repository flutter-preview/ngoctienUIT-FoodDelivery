package com.tnt.food_delivery.repository;

import com.tnt.food_delivery.data.model.Register;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RegisterRepository extends MongoRepository<Register, String> {
    @Query("{ type: { $regex: ?0 }, status: { $regex: ?1 } }")
    List<Register> findItem(String type, String status);
}
