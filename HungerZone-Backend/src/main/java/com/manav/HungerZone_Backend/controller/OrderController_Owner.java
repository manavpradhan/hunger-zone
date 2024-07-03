package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.exception.OrderException;
import com.manav.HungerZone_Backend.model.Order;
import com.manav.HungerZone_Backend.service.interfaces.OrderService;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class OrderController_Owner {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @DeleteMapping("/order/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long orderId) throws OrderException {
        if(orderId!=null) {
            orderService.cancelOrder(orderId);
            return ResponseEntity.ok("Order deleted with id)"+orderId);
        }else return new ResponseEntity<String>(HttpStatus.BAD_REQUEST) ;
    }


    @GetMapping("/order/restaurant/{restaurantId}")
    public ResponseEntity<List<Order>> getAllRestaurantOrders(
            @PathVariable Long restaurantId,
            @RequestParam(required = false) String order_status) throws OrderException {

        List<Order> orders = orderService.getRestaurantOrders(restaurantId, order_status);

        return ResponseEntity.ok(orders);

    }

    @PutMapping("/orders/{orderId}/{orderStatus}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long orderId,@PathVariable String orderStatus) throws OrderException{

        Order orders = orderService.updateOrder(orderId, orderStatus);

        return ResponseEntity.ok(orders);

    }
}
