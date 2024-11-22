package com.esd_project.helper;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JWTHelper {
    private String SECRET_KEY = "cr666N7wIV+KJ2xOQpWtcfAekL4YXd9gbnJMs8SJ9sI=";

    // FUNCTION FOR GETTING USER'S NAME FROM THE TOKEN
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // FUNCTION FOR GETTING EXPIRATION DATE FROM THE TOKEN
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // FUNCTION FOR GETTING ALL CLAIMS
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // FUNCTION FOR GETTING CLAIM
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) { // claimsResolver is a function that specifies which claim to extract
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // CHECKING IF THE TOKEN IN EXPIRED
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // FUNCTION FOR TOKEN GENERATION
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    // FUNCTION FOR CREATING TOKEN FROM CLAIM
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Token valid for 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    // FUNCTION FOR VALIDATING TOKEN
    public Boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }
}
