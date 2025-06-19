package com.brucecompiler.utils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


/**
 * Utility class for JWT (JSON Web Token) operations
 * Provides methods to generate and parse JWT tokens
 */
public class JwtUtil {

    /**
     * Generate a JWT token with the specified claims
     *
     * @param secretKey The secret key used to sign the JWT
     * @param expiration The token expiration time in milliseconds
     * @param claims The map of claims to include in the JWT payload
     * @return The generate JWT token string
     */
    public static String generateJWT(String secretKey, Long expiration, Map<String, Object> claims) {

        // Build the JWT with claims, signature algorithm, secret key, and expiration time
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .compact();
    }

    /**
     * Parse and validates a JWT token
     *
     * @param jwt The JWT token string to parse
     * @param secretKey The secret key used to verify the JWT signature
     * @return The parsed Claims object containing the JWT payload
     */
    public static Claims parseJWT(String jwt, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwt).getBody();
    }
}
