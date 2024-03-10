package com.datmt.security.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;
@Component
public class JwtUtil {
   
    private static final long EXPIRATION_TIME = 86_400_000; // JWT expiration time in milliseconds (e.g., 24h)
    private final String SECRET_KEY = "Xx0UNmlhlk0uv8LQN3UMSIIL0foH00D9wpK1qyzAZLY="; // Use a proper secret key,

    public String createToken(Authentication authentication) {
        // Extract username and roles from the Authentication object
        String username = authentication.getName();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        // Specify token generation date and expiration date
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        // Generate JWT token
        return Jwts.builder()
                .setSubject(username) // Set the username as subject
                .claim("roles", roles) // Add roles as a claim
                .setIssuedAt(now) // Set the issue date
                .setExpiration(expiryDate) // Set the expiration date
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256) // Sign the token with HS512 algorithm and secret key
                .compact(); // Build the token
    }

    public Boolean validateToken(String token, String username) {
        final String usernameFromToken = getUsernameFromToken(token);
        return (username.equals(usernameFromToken) && !isTokenExpired(token));
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        JwtParserBuilder parserBuilder = Jwts.parserBuilder();
        parserBuilder.setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()));
        return parserBuilder.build().parseClaimsJws(token).getBody();
    } 
}
