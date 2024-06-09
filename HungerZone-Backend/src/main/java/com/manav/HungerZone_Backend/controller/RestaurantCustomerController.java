package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.dto.RestaurantDto;
import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.exception.UserException;
import com.manav.HungerZone_Backend.model.Restaurant;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.service.interfaces.RestaurantService;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantCustomerController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<Restaurant>> getAllRestaurants(){
        List<Restaurant> allRestaurants = restaurantService.getAllRestaurants();

        return new ResponseEntity<>(allRestaurants, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable("id") Long restaurantId) throws RestaurantException {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Restaurant>> findRestaurantBySearch(
            @RequestParam("keyword") String query) {
        List<Restaurant> restaurants = restaurantService.searchRestaurant(query);

        return new ResponseEntity<>(restaurants, HttpStatus.OK);
    }

    @PutMapping("/{id}/add-favorites")
    public ResponseEntity<RestaurantDto> addToFavorite(
            @RequestHeader("Authorization") String jwt,
            @PathVariable("id") Long restaurantId) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        RestaurantDto restaurant = restaurantService.addToFavorites(restaurantId, user);

        return new ResponseEntity<>(restaurant, HttpStatus.OK);
    }
}
