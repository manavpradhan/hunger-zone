package com.manav.HungerZone_Backend.controller;

import com.manav.HungerZone_Backend.config.JwtProvider;
import com.manav.HungerZone_Backend.domain.USER_ROLE;
import com.manav.HungerZone_Backend.exception.UserException;
import com.manav.HungerZone_Backend.model.Cart;
import com.manav.HungerZone_Backend.model.User;
import com.manav.HungerZone_Backend.repository.CartRepository;
import com.manav.HungerZone_Backend.repository.UserRepository;
import com.manav.HungerZone_Backend.request.LoginRequest;
import com.manav.HungerZone_Backend.response.AuthResponse;
import com.manav.HungerZone_Backend.service.CustomerUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Autowired
    private CartRepository cartRepository;


    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException {

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();
        USER_ROLE role = user.getRole();

        User doesEmailExist = userRepository.findByEmail(email);
        if (doesEmailExist!=null) {
            throw new UserException("Email is already being used with another account");
        }

        // Create new user
        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setFullName(fullName);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setRole(role);

        User savedUser = userRepository.save(createdUser);

        //Setting up cart
        Cart cart = new Cart();
        cart.setCustomer(savedUser);

        Cart savedCart = cartRepository.save(cart);


        //Authenticating user
        List<GrantedAuthority> authorities=new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(role.toString()));


        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(token);
        authResponse.setMessage("Register Success");
        authResponse.setRole(savedUser.getRole());

        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUser(@RequestBody LoginRequest req) {

        String email = req.getEmail();
        String password = req.getPassword();

        Authentication authentication = authenticate(email, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwtToken = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtToken);
        authResponse.setMessage("login success");

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String roleName = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        authResponse.setRole(USER_ROLE.valueOf(roleName));

        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }

    private Authentication authenticate(String username, String password){
        UserDetails userDetails = customerUserDetailsService.loadUserByUsername(username);

        if(userDetails == null){
            throw new BadCredentialsException("Invalid username or password");
        }

        System.out.println("user details: "+userDetails);

        if(! passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Invalid username or password");
        }

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
