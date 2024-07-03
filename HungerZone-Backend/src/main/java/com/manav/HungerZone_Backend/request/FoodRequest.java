package com.manav.HungerZone_Backend.request;

import com.manav.HungerZone_Backend.model.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodRequest {

    private String name;
    private String description;
    private Long price;


    private Category category;
    private List<String> images;


    private Long restaurantId;

    private boolean vegetarian;
    private boolean seasonal;
}
