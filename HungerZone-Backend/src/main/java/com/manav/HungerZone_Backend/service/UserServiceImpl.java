package com.manav.HungerZone_Backend.service;

import com.manav.HungerZone_Backend.config.JwtProvider;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.repository.UserRepository;
import com.manav.HungerZone_Backend.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String token) throws Exception{

        String email = jwtProvider.getEmailFromJwtToken(token);

        User user = findUserByEmail(email);

        return user;
    }

    @Override
    public User findUserByEmail(String email) throws Exception{
        User user = userRepository.findByEmail(email);

        if(user == null){
            throw new Exception("User with this Email doesn't exist");
        }

        return user;
    }
}
