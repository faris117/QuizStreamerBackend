package com.example.auth_service.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    @Autowired
    private UserDetailsService userDetailsService;

    private final String SECRETKEY="5657d7fbfe9d3e5ac6166065b476d35a9ffe06b47739a49543294f9995b8e158";

    public String createToken(String username) {
       UserDetails userDetails= userDetailsService.loadUserByUsername(username);
        Map<String,Object> claims=new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
       return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*30))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token){
        return !isTokenExpired(token);
    }

    public SecretKey getSecretKey(){
        byte[] keyBytes=Decoders.BASE64.decode(SECRETKEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        System.out.println(extractExpiration(token));
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token,Function<Claims,T> resolver){
        Claims claims=extractAllClaims(token);
        return resolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        var parser=  Jwts.parser().verifyWith(getSecretKey()).build();
        return parser.parseSignedClaims(token).getPayload();
    }
}
