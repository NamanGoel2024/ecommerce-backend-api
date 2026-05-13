package ecommerce_backend_api.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET =
            "myverysecuresecretkeymyverysecuresecretkey";

    private static final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public static SecretKey getKey() {
        return key;
    }

    public static String generateToken(String email) {

        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key)
                .compact();
    }
}