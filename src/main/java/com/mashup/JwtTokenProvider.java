package com.mashup;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final String jwtSecret="secret";

    public String generateToken(Authentication authentication) {
    	Authentication loggedInUser=	SecurityContextHolder.getContext().getAuthentication();
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 864_000_000);
        return Jwts.builder().setIssuer( loggedInUser.getName().toString())
                .setSubject( loggedInUser.getName().toString())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

}
