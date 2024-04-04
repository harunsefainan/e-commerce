package com.harunsefainan.ecommerce.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * This class manages JWT (JSON Web Token) generation, validation, and claims parsing operations.
 */
@Service
public class JwtTokenManager {
    private final int VALIDITY = 5 * 60 * 1000; //Determines the validity period of the token.
    private final String ISSUER = "com.harunsefainan.ecommerce"; //Specifies the name of the organization or entity that issues or provides the token.
    private SecretKey KEY = Jwts.SIG.HS256.key().build(); //Specifies the secret key used to sign and verify the token.


    // This method generates a JWT (JSON Web Token) from the username.
    public String generateToken(String username) {
        String jws = Jwts.builder()
                .subject(username)
                .issuer(ISSUER)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + VALIDITY))
                .signWith(KEY)
                .compact();

        return jws;
    }

    // This method validates a JWT (JSON Web Token) by checking if it has a valid username and is not expired.
    public boolean tokenValidate(String token) {
        if (getUsernameToken(token) != null && isExpired(token)) {
            return true;
        }
        return false;
    }

    // This method extracts the username from a JWT (JSON Web Token).
    public String getUsernameToken(String token) {
        Claims claims = getClaims(token);
        return claims.getSubject();
    }

    // This method checks if a JWT (JSON Web Token) has expired.
    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    // This method parses and verifies a JWT (JSON Web Token) to extract its claims (payload).
    private Claims getClaims(String token) {
        Claims claims = Jwts.parser().verifyWith(KEY).build().parseSignedClaims(token).getPayload();
        return claims;
    }

}
