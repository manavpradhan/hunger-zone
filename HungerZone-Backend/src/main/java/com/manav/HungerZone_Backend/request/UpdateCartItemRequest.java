package com.manav.HungerZone_Backend.request;

import lombok.Data;

@Data
public class UpdateCartItemRequest {
    private Long cartId;
    private int quantity;
}
