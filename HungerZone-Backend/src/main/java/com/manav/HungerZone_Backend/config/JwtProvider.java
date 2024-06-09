package com.manav.HungerZone_Backend.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class JwtProvider {

    private final SecretKey key = Keys.hmacShaKeyFor(JwtConstant.JWT_SECRET.getBytes());

    public String generateToken(Authentication auth){
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        String token = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();

        return token;
    }

    public String getEmailFromJwtToken(String token){
        token = token.substring(7);

        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String email = String.valueOf(claims.get("email"));

        return email;
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> auths=new HashSet<>();

        for(GrantedAuthority authority:collection) {
            auths.add(authority.getAuthority());
        }
        return String.join(",",auths);
    }
}
