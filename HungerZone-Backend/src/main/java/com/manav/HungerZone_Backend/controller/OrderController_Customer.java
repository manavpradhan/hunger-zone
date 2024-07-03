package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.model.Order;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.request.CreateOrderRequest;
import com.manav.HungerZone_Backend.service.interfaces.OrderService;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController_Customer {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody CreateOrderRequest req, @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);

        Order newOrder = orderService.createOrder(req, user);

        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @GetMapping("/order/user")
    public ResponseEntity<List<Order>> getOrderHistory( @RequestHeader("Authorization") String token) throws Exception {

        User user = userService.findUserByJwtToken(token);

        List<Order> allOrders = orderService.getUserOrders(user.getId());

        return new ResponseEntity<>(allOrders, HttpStatus.OK);
    }
}
