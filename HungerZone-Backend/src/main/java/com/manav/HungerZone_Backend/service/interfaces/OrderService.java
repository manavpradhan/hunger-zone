package com.manav.HungerZone_Backend.service.interfaces;

import com.manav.HungerZone_Backend.exception.CartException;
import com.manav.HungerZone_Backend.exception.OrderException;
import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.exception.UserException;
import com.manav.HungerZone_Backend.model.Order;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {

    public Order createOrder(CreateOrderRequest order, User user) throws RestaurantException, CartException, UserException;

    public Order updateOrder(Long orderId, String orderStatus) throws OrderException;

    public void cancelOrder(Long orderId) throws OrderException;

    public List<Order> getUserOrders(Long userId) throws OrderException;

    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws OrderException;

}
