package com.manav.HungerZone_Backend.request;

import com.manav.HungerZone_Backend.model.ContactInformation;
import com.manav.HungerZone_Backend.model.UserAddress;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantRequest {

    private Long id;
    private String name;
    private String description;
    private String cuisineType;
    private UserAddress address;
    private ContactInformation  contactInformation;
    private String openingHours;
    private List<String> images;
    private LocalDateTime registrationDate;
}
