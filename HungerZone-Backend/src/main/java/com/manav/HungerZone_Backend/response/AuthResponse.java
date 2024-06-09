package com.manav.HungerZone_Backend.response;

import com.manav.HungerZone_Backend.domain.USER_ROLE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String message;
    private String jwtToken;
    private USER_ROLE role;
}
