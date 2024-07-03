package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.exception.FoodException;
import com.manav.HungerZone_Backend.model.Food;
import com.manav.HungerZone_Backend.service.interfaces.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food")
public class MenuController_Customer {
    
    @Autowired
    private FoodService menuItemService;

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchMenuItem(@RequestParam("keyword") String name)  {

        List<Food> menuItem = menuItemService.searchFood(name);

        return new ResponseEntity<>(menuItem, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Food>> getMenuByRestaurantId(
            @PathVariable Long restaurantId,
            @RequestParam boolean vegetarian,
            @RequestParam boolean seasonal,
            @RequestParam boolean nonVeg,
            @RequestParam(required = false) String food_category) throws FoodException {


        List<Food> menuItems= menuItemService.getFilteredFood(restaurantId,vegetarian, nonVeg,seasonal,food_category);

        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }
}
