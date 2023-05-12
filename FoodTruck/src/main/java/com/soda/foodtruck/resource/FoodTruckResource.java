package com.soda.foodtruck.resource;

import com.soda.foodtruck.model.FoodTruckModel;
import com.soda.foodtruck.service.IFoodTruck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController("/api")
public class FoodTruckResource {

    @Autowired
    IFoodTruck foodTruck;
    @GetMapping("/echo")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/foodtrucks")
    public ResponseEntity<?> getAllFoodTrucks(@RequestParam(name ="status", required=false) String status) throws Exception{

        var foodTrucks = foodTruck.findAllFoodTrucks(status);

        return foodTrucks;
    }
}
