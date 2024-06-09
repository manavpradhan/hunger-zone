package com.manav.HungerZone_Backend.response;

import com.manav.HungerZone_Backend.domain.USER_ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private boolean status;
}
