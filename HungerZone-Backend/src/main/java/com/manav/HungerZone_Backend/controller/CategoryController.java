package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.model.Category;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.service.interfaces.CategoryService;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @Autowired
    public UserService userService;

    @PostMapping("/admin/category")
    public ResponseEntity<Category> createdCategory(@RequestHeader("Authorization")String jwt, @RequestBody Category category) throws Exception, RestaurantException {

        User user = userService.findUserByJwtToken(jwt);

        Category createdCategory = categoryService.createCategory(category.getName(), user.getId());

        return new ResponseEntity<>(createdCategory, HttpStatus.OK);
    }

    @GetMapping("/category/restaurant/{id}")
    public ResponseEntity<List<Category>> getRestaurantsCategory(
            @PathVariable Long id,
            @RequestHeader("Authorization")String jwt) throws Exception, RestaurantException {

        User user = userService.findUserByJwtToken(jwt);
        List<Category> categories = categoryService.findCategoryByRestaurantId(id);

        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
}
