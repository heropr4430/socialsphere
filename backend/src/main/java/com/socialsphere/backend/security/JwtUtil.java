package com.socialsphere.backend.security;

import com.socialsphere.backend.exceptions.InvalidParamException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.expiration}")
    private int expiration; //save to an environment variable
    @Value("${jwt.secretKey}")
    private String secretKey;

    public String generateToken(com.socialsphere.backend.models.User user) throws Exception{
        //properties => claims
        Map<String, Object> claims = new HashMap<>();
        //this.generateSecretKey();
        claims.put("username", user.getUsername());
        claims.put("userId", user.getId());
        try {
            String token = Jwts.builder()
                    .setClaims(claims) //how to extract claims from this ?
                    .setSubject(user.getUsername())
                    .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                    .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()), SignatureAlgorithm.HS256)
                    .compact();
            return token;
        }catch (Exception e) {
            //you can "inject" Logger, instead System.out.println
            throw new InvalidParamException("Cannot create jwt token, error: "+e.getMessage());
            //return null;
        }
    }

}
