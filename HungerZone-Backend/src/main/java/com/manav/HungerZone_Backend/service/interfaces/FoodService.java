package com.manav.HungerZone_Backend.service.interfaces;

import com.manav.HungerZone_Backend.exception.FoodException;
import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.model.Category;
import com.manav.HungerZone_Backend.model.Food;
import com.manav.HungerZone_Backend.model.Restaurant;
import com.manav.HungerZone_Backend.request.FoodRequest;

import java.util.List;

public interface FoodService {

    public Food createFood(FoodRequest req, Category category, Restaurant restaurant) throws FoodException, RestaurantException;

    void deleteFood(Long foodId) throws FoodException;

    public List<Food> getFilteredFood(Long restaurantId,
                                         boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException;

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws FoodException;

    public Food updateAvailabilityStatus(Long foodId) throws FoodException;
}
