package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.exception.FoodException;
import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.model.Food;
import com.manav.HungerZone_Backend.model.Restaurant;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.request.FoodRequest;
import com.manav.HungerZone_Backend.service.interfaces.FoodService;
import com.manav.HungerZone_Backend.service.interfaces.RestaurantService;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class MenuController_Owner {

    @Autowired
    private FoodService menuItemService;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Food> createMenuItem(@RequestBody FoodRequest item, @RequestHeader("Authorization") String jwt)
            throws Exception, RestaurantException, FoodException {

        //System.out.println("req-controller ----"+item);

        User user = userService.findUserByJwtToken(jwt);

//		Category category=categoryService.findCategoryById(item.getCategoryId());

        Restaurant restaurant=restaurantService.findRestaurantById(item.getRestaurantId());

        Food menuItem = menuItemService.createFood(item,item.getCategory(),restaurant);

        return new ResponseEntity<>(menuItem, HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMenuItem(@PathVariable("id") Long id, @RequestHeader("Authorization") String jwt)
            throws Exception, FoodException {

        User user = userService.findUserByJwtToken(jwt);

        menuItemService.deleteFood(id);

        return new ResponseEntity<>("Menu item deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Food> updateAvailabilityStatus(@PathVariable("id") Long id) throws FoodException {

        Food menuItems= menuItemService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(menuItems, HttpStatus.CREATED);
    }
}
