package com.soda.foodtruck.service;

import com.soda.foodtruck.model.FoodTruckModel;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IFoodTruck {
    ResponseEntity<?> findAllFoodTrucks(String status) throws Exception;
}
