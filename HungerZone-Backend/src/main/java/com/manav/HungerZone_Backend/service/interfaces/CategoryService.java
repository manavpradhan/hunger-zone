package com.manav.HungerZone_Backend.service.interfaces;

import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.model.Category;

import java.util.List;

public interface CategoryService {

    public Category createCategory (String name, Long userId) throws RestaurantException;
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException;
    public Category findCategoryById(Long id) throws RestaurantException;

}
