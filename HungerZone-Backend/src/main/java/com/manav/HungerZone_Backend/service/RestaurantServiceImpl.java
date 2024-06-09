package com.manav.HungerZone_Backend.service;

import com.manav.HungerZone_Backend.dto.RestaurantDto;
import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.model.Restaurant;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.model.UserAddress;
import com.manav.HungerZone_Backend.repository.AddressRepository;
import com.manav.HungerZone_Backend.repository.RestaurantRepository;
import com.manav.HungerZone_Backend.repository.UserRepository;
import com.manav.HungerZone_Backend.request.RestaurantRequest;
import com.manav.HungerZone_Backend.service.interfaces.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Restaurant createRestaurant(RestaurantRequest req, User user) {

        UserAddress address=new UserAddress();
        address.setCity(req.getAddress().getCity());
        address.setCountry(req.getAddress().getCountry());
        address.setFullName(req.getAddress().getFullName());
        address.setPostalCode(req.getAddress().getPostalCode());
        address.setState(req.getAddress().getState());
        address.setStreetAddress(req.getAddress().getStreetAddress());
        UserAddress savedAddress = addressRepository.save(address);

        Restaurant restaurant = new Restaurant();

        restaurant.setRestaurantAddress(savedAddress);
        restaurant.setContactInformation(req.getContactInformation());
        restaurant.setCuisineType(req.getCuisineType());
        restaurant.setDescription(req.getDescription());
        restaurant.setImages(req.getImages());
        restaurant.setName(req.getName());
        restaurant.setOpeningHours(req.getOpeningHours());
        restaurant.setRegistrationDate(req.getRegistrationDate());
        restaurant.setOwner(user);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        return savedRestaurant;
    }

    @Override
    public Restaurant findRestaurantById(Long restaurantId) throws RestaurantException {

        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurantId);
        if (restaurant.isPresent()) {
            return restaurant.get();
        } else {
            throw new RestaurantException("Restaurant with id " + restaurantId + "not found");
        }
    }

    @Override
    public Restaurant findRestaurantsByUserId(Long userId) throws RestaurantException {

        Restaurant restaurants = restaurantRepository.findByOwnerId(userId);

        if(restaurants == null){
            throw new RestaurantException("Restaurant with OwnerId " + userId + "not found");
        }

        return restaurants;
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, RestaurantRequest updatedReq) throws RestaurantException {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant == null){
            throw new RestaurantException("Restaurant with id " + restaurantId + "not found");
        }

        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedReq.getCuisineType());
        }

        if(restaurant.getDescription() != null){
            restaurant.setDescription(updatedReq.getDescription());
        }

        return restaurantRepository.save((restaurant));
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws RestaurantException {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant != null){
            restaurantRepository.delete(restaurant);
            return;
        }
        throw new RestaurantException("Restaurant with id" + restaurantId + "not found");
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepository.findBySearchQuery(keyword);
    }


    @Override
    public RestaurantDto addToFavorites(Long restaurantId, User user) throws RestaurantException {

        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant == null){
            throw new RestaurantException("Restaurant with id " + restaurantId + "not found");
        }

        RestaurantDto dto = new RestaurantDto();
        dto.setTitle(restaurant.getName());
        dto.setImages(restaurant.getImages());
        dto.setId(restaurant.getId());
        dto.setDescription(restaurant.getDescription());

        boolean isFavorite = false;

        List<RestaurantDto> favorites = user.getFavorites();

        for(RestaurantDto fav: favorites){
            if(fav.getId().equals(restaurantId)){
                isFavorite = true;
                break;
            }
        }
        if(isFavorite){
            favorites.removeIf(fav -> fav.getId().equals(restaurantId));
        }else{
            favorites.add(dto);
        }

        user.setFavorites(favorites);

        userRepository.save(user);

        return dto;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long restaurantId) throws RestaurantException {

        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant == null){
            throw new RestaurantException("Restaurant with id " + restaurantId + "not found");
        }

        restaurant.setOpen(!restaurant.isOpen());
        return restaurantRepository.save(restaurant);
    }
}
