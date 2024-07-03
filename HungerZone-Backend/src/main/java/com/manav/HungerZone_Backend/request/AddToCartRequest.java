package com.manav.HungerZone_Backend.request;

import lombok.Data;

@Data
public class AddToCartRequest {
    private Long menuItemId;
    private int quantity;
}
