package com.manav.HungerZone_Backend.service;

import com.manav.HungerZone_Backend.exception.CartException;
import com.manav.HungerZone_Backend.exception.OrderException;
import com.manav.HungerZone_Backend.exception.RestaurantException;
import com.manav.HungerZone_Backend.exception.UserException;
import com.manav.HungerZone_Backend.model.*;
import com.manav.HungerZone_Backend.repository.*;
import com.manav.HungerZone_Backend.request.CreateOrderRequest;
import com.manav.HungerZone_Backend.service.interfaces.CartService;
import com.manav.HungerZone_Backend.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(CreateOrderRequest order, User user) throws RestaurantException, CartException, UserException {

        UserAddress shipAddress = order.getDeliveryAddress();
        UserAddress savedAddress;

        if(!user.getAddresses().contains(shipAddress)) {
            user.getAddresses().add(shipAddress);
            savedAddress = addressRepository.save(shipAddress);
            userRepository.save(user);
        }else{
            savedAddress = shipAddress;
        }
        System.out.println("user addresses --------------  "+user.getAddresses());


        Optional<Restaurant> restaurant = restaurantRepository.findById(order.getRestaurantId());
        if(restaurant.isEmpty()) {
            throw new RestaurantException("Restaurant not found with id "+order.getRestaurantId());
        }

        Cart cart = cartService.findCartByUserId(user.getId());

        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setFood(cartItem.getFood());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setTotalPrice(cartItem.getFood().getPrice()* cartItem.getQuantity());

            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(savedOrderItem);
        }
        Long totalPrice = cartService.calculateCartTotal(cart);


        Order createdOrder = new Order();

        createdOrder.setCustomer(user);
        createdOrder.setDeliveryAddress(savedAddress);
        createdOrder.setCreatedAt(new Date());
        createdOrder.setOrderStatus("PENDING");
        createdOrder.setRestaurant(restaurant.get());
        createdOrder.setItems(orderItems);
        createdOrder.setTotalAmount(totalPrice);

        Order savedOrder = orderRepository.save(createdOrder);

        restaurant.get().getOrders().add(savedOrder);

        return savedOrder;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) throws OrderException {

        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()){
            throw new OrderException("Order not found");
        }

        System.out.println("--------- "+orderStatus);

        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")){
            order.get().setOrderStatus(orderStatus);
        }

        return orderRepository.save(order.get());
    }

    @Override
    public void cancelOrder(Long orderId) throws OrderException {

        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()){
            throw new OrderException("Order not found");
        }

        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getUserOrders(Long userId) throws OrderException {

        List<Order> allUserOrders = orderRepository.findAllUserOrders(userId);

        return allUserOrders;
    }

    @Override
    public List<Order> getRestaurantOrders(Long restaurantId, String orderStatus) throws OrderException {

        List<Order> allRestaurantOrders = orderRepository.findAllRestaurantOrders(restaurantId);

        if (orderStatus != null){
            allRestaurantOrders = allRestaurantOrders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return allRestaurantOrders;
    }
}
