package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JWTUtil {
    private final String SECRET =
            "aVeryLongRandomSecretKeyForJwtTokenGeneration1234567890";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private final long EXPIRATION_TIME = 1000*60*60;

    public String generateToken(String username) {
        return Jwts.builder().setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key , SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractusername(String token) {

        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public boolean validateToken(String username, UserDetails userDetails , String token) {

        // TODO - check if username is same as user name in userDetails ?
        // TODO - check if token is not expired

        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token){
        return extractClaims(token).getExpiration().before(new Date());
    }
}
