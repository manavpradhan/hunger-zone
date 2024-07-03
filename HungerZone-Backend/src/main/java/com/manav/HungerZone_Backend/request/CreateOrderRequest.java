package com.manav.HungerZone_Backend.request;

import com.manav.HungerZone_Backend.model.UserAddress;
import lombok.Data;

@Data
public class CreateOrderRequest {
    private Long restaurantId;
    private UserAddress deliveryAddress;
}
