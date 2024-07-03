package com.manav.HungerZone_Backend.repository;

import com.manav.HungerZone_Backend.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT o FROM Order o WHERE o.customer.id = :customerId")
    public List<Order> findAllUserOrders(@Param("customerId") Long customerId);

    @Query("SELECT o FROM Order o WHERE o.restaurant.id = :restaurantId")
    public List<Order> findAllRestaurantOrders(@Param("restaurantId") Long restaurantId);
}
