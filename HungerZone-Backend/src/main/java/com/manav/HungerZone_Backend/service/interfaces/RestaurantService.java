package com.manav.HungerZone_Backend.service.interfaces;

import com.manav.HungerZone_Backend.dto.RestaurantDto;
import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.model.Restaurant;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.request.RestaurantRequest;

import java.util.List;

public interface RestaurantService {

    // Restaurant owner methods
    public Restaurant findRestaurantsByUserId(Long userId) throws RestaurantException;
    public Restaurant createRestaurant(RestaurantRequest req, User user);
    public Restaurant updateRestaurant(Long restaurantId, RestaurantRequest updatedReq) throws RestaurantException;
    public void deleteRestaurant(Long restaurantId) throws RestaurantException;
    public Restaurant updateRestaurantStatus(Long restaurantId)throws RestaurantException;


    // Restaurant customer methods
    public List<Restaurant> getAllRestaurants();
    public List<Restaurant>searchRestaurant(String keyword);
    public Restaurant findRestaurantById(Long restaurantId) throws RestaurantException;
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws RestaurantException;
}
