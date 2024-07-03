package com.manav.HungerZone_Backend.service;

import com.manav.HungerZone_Backend.exception.FoodException;
import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.model.Category;
import com.manav.HungerZone_Backend.model.Food;
import com.manav.HungerZone_Backend.model.Restaurant;
import com.manav.HungerZone_Backend.repository.FoodRepository;
import com.manav.HungerZone_Backend.repository.RestaurantRepository;
import com.manav.HungerZone_Backend.request.FoodRequest;
import com.manav.HungerZone_Backend.service.interfaces.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Food createFood(FoodRequest req, Category category, Restaurant restaurant) throws FoodException, RestaurantException{
        Food food=new Food();
        food.setFoodCategory(category);
        food.setCreationDate(new Date());
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice((long) req.getPrice());
        food.setSeasonal(req.isSeasonal());
        food.setVegetarian(req.isVegetarian());
        food.setRestaurant(restaurant);
        food = foodRepository.save(food);

        restaurant.getFoods().add(food);

        return food;
    }

    @Override
    public void deleteFood(Long foodId) throws FoodException {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);
        foodRepository.delete(food);
    }

    @Override
    public List<Food> getFilteredFood(Long restaurantId, boolean isVegetarian, boolean isNonveg, boolean isSeasonal, String foodCategory) throws FoodException {

        List<Food> foods = foodRepository.findByRestaurantId(restaurantId);

        if (isVegetarian) {
            foods = filterByVegetarian(foods, isVegetarian);
        }
        if (isNonveg) {
            foods = filterByNonveg(foods, isNonveg);
        }

        if (isSeasonal) {
            foods = filterBySeasonal(foods, isSeasonal);
        }
        if(foodCategory!=null && !foodCategory.equals("")) {
            foods = filterByFoodCategory(foods, foodCategory);
        }

        return foods;
    }

    @Override
    public List<Food> searchFood(String keyword) {
        List<Food> items=new ArrayList<>();

        if(keyword != "") {
//            System.out.println("keyword -- "+keyword);
            items = foodRepository.searchByNameOrCategory(keyword);
        }

        return items;
    }

    @Override
    public Food findFoodById(Long foodId) throws FoodException {
        Optional<Food> food = foodRepository.findById(foodId);
        if (food.isPresent()) {
            return food.get();
        }
        throw new FoodException("food with id" + foodId + "not found");
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws FoodException {
        Food food = findFoodById(foodId);

        food.setAvailable(!food.isAvailable());
        foodRepository.save(food);

        return food;
    }

    private List<Food> filterByVegetarian(List<Food> foods, boolean isVegetarian) {
        return foods.stream()
                .filter(food -> food.isVegetarian() == isVegetarian)
                .collect(Collectors.toList());
    }
    private List<Food> filterByNonveg(List<Food> foods, boolean isNonveg) {
        return foods.stream()
                .filter(food -> food.isVegetarian() == false)
                .collect(Collectors.toList());
    }
    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream()
                .filter(food -> food.isSeasonal() == isSeasonal)
                .collect(Collectors.toList());
    }
    private List<Food> filterByFoodCategory(List<Food> foods, String foodCategory) {

        return foods.stream()
                .filter(food -> {
                    if (food.getFoodCategory() != null) {
                        return food.getFoodCategory().getName().equals(foodCategory);
                    }
                    return false; // Return true if food category is null
                })
                .collect(Collectors.toList());
    }
}
