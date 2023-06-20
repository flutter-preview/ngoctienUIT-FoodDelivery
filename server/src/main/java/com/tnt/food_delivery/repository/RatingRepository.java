package com.tnt.food_delivery.repository;

import com.tnt.food_delivery.data.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RatingRepository extends MongoRepository<Rating, String>  {
}
