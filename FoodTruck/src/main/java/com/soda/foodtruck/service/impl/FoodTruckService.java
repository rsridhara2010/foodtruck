package com.soda.foodtruck.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soda.foodtruck.model.FoodTruckModel;
import com.soda.foodtruck.service.IFoodTruck;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class FoodTruckService implements IFoodTruck {

    ObjectMapper mapper = new ObjectMapper();

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public ResponseEntity<?> findAllFoodTrucks(String status) throws Exception{
        URI url = new URI("https://data.sfgov.org/resource/rqzj-sfat.json");
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        if(StringUtils.hasText(status)){
            var allFoodTrucks =Arrays.asList( mapper.readValue(response.getBody(),FoodTruckModel[].class));
            var foodTructsByStatus = allFoodTrucks.stream().filter(p->p.status.equals(status)).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(foodTructsByStatus)){
                return new ResponseEntity<>(foodTructsByStatus, HttpStatus.OK);
            }else{
                return new ResponseEntity<>("There are no food trucks by the status", HttpStatus.NOT_FOUND);
            }
        }
        return response;
    }

}
