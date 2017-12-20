package com.hw.util.security;

import com.google.common.io.BaseEncoding;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;
import java.util.Optional;

@Component
public class TokenHandler {
    private final SecretKey secretKey;
    private final SecretKey secretKeyForUserInfo;

    public TokenHandler() {
        String jwtKey = "dimasyarikleha12";
        String jwtKey2 = "dimasyarikleha12";
        byte[] decodedKey = BaseEncoding.base64().decode(jwtKey);
        byte[] decodedKey2 = BaseEncoding.base64().decode(jwtKey2);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
        secretKeyForUserInfo = new SecretKeySpec(decodedKey2, 0, decodedKey2.length, "AES");
    }

//    private SecretKey generateSecretKey(String key) {
//        byte[] decodedKey = BaseEncoding.base64().decode(key);
//    }

    public Optional<Long> extractUserId(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();
            return Optional
                    .ofNullable(body.getId())
                    .map(Long::new);

        } catch (RuntimeException e) {
            return Optional.empty();
        }

    }

    public String generateAccessToken(Long id, LocalDateTime expires) {
        return Jwts.builder()
                .setId(id.toString())
                .setExpiration(Date.from(expires.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact();
    }


    public String encode(Map<String, Object> object) {
        return Jwts.builder()
                .setClaims(object)
                .signWith(SignatureAlgorithm.HS512, secretKeyForUserInfo)
                .compact();
    }

    public Map<String, Object> decode(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKeyForUserInfo).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body;
    }
}
