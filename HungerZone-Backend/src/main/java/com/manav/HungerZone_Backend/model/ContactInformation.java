package com.manav.HungerZone_Backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactInformation {

    private String email;
    private String mobile;
    private String twitter;
    private String instagram;
}
