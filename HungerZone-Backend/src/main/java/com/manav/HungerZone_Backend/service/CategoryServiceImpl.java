package com.manav.HungerZone_Backend.service;

import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.model.Category;
import com.manav.HungerZone_Backend.model.Restaurant;
import com.manav.HungerZone_Backend.repository.CategoryRepository;
import com.manav.HungerZone_Backend.service.interfaces.CategoryService;
import com.manav.HungerZone_Backend.service.interfaces.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(String name, Long userId) throws RestaurantException {

        Restaurant restaurant = restaurantService.findRestaurantsByUserId(userId);
        Category createdCategory = new Category();

        createdCategory.setName(name);
        createdCategory.setRestaurant(restaurant);

        return categoryRepository.save(createdCategory);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long restaurantId) throws RestaurantException {

        List<Category> categories = categoryRepository.findByRestaurantId(restaurantId);

        return categories;
    }

    @Override
    public Category findCategoryById(Long id) throws RestaurantException {

        Optional<Category> category = categoryRepository.findById(id);

        if(category.isEmpty()){
            throw new RestaurantException("this category is not available");
        }

        return category.get();
    }
}
