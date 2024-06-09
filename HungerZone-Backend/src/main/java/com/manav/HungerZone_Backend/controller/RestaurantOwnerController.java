package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.model.Restaurant;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.request.RestaurantRequest;
import com.manav.HungerZone_Backend.response.ApiResponse;
import com.manav.HungerZone_Backend.service.interfaces.RestaurantService;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurant")
public class RestaurantOwnerController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping("/my-restaurant")
    public ResponseEntity<Restaurant> getRestaurantByJwtToken(@RequestHeader("Authorization") String jwtToken) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        Restaurant restaurant = restaurantService.findRestaurantsByUserId(user.getId());

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantRequest req, @RequestHeader("Authorization") String jwtToken) throws Exception {
        User user = userService.findUserByJwtToken(jwtToken);
        Restaurant restaurant = restaurantService.createRestaurant(req, user);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantRequest updatedReq) throws Exception {

        Restaurant restaurant = restaurantService.updateRestaurant(id, updatedReq);

        return new ResponseEntity<>(restaurant, HttpStatus.ACCEPTED);
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteRestaurant(@PathVariable("id") Long restaurantId) throws Exception {

        restaurantService.deleteRestaurant(restaurantId);

        ApiResponse apiResponse = new ApiResponse("Restaurant deleted successfully", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Restaurant> updateRestaurantStatus(@PathVariable("id") Long restaurantId) throws Exception {

        Restaurant restaurant = restaurantService.updateRestaurantStatus(restaurantId);

        return new ResponseEntity<>(restaurant, HttpStatus.ACCEPTED);
    }
}