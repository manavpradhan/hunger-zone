package com.manav.HungerZone_Backend.service.interfaces;

import com.manav.HungerZone_Backend.model.User;

public interface UserService {
    public User findUserByJwtToken(String token) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
