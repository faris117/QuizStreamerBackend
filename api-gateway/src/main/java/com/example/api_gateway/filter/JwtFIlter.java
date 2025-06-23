package com.example.api_gateway.filter;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

public class JwtFIlter {
   private final String SECRETKEY="5657d7fbfe9d3e5ac6166065b476d35a9ffe06b47739a49543294f9995b8e158";

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


public <T> T extractClaim(String token,Function<Claims,T> resolver){
        Claims claims=extractAllClaims(token);
        return resolver.apply(claims);
    }

    public Claims extractAllClaims(String token){
        var parser=  Jwts.parser().verifyWith(getSecretKey()).build();
        return parser.parseSignedClaims(token).getPayload();
    }


}
