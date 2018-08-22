package com.mashup;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

	private final String secret="secret";

	private final String token_prefix="Bearer ";

	private final String header_string="Authorization";

	 public JwtAuthorizationFilter( AuthenticationManager authManager) {
	        super(authManager);
	    }

	    @Override
	    protected void doFilterInternal(HttpServletRequest req,
	                                    HttpServletResponse res,
	                                    FilterChain chain) throws IOException, ServletException {
	        String header = req.getHeader(header_string);

	        if (header == null || !header.startsWith(token_prefix)) {
	            chain.doFilter(req, res);
	            return;
	        }

	        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        chain.doFilter(req, res);
	    }

	    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
	        String token = request.getHeader(header_string);
	        if (token != null) {
	            // parse the token.
	        	byte[] encodedBytes = Base64.decode(secret.getBytes());
	        	
	        	
	            String user = JWT.require(Algorithm.HMAC512(encodedBytes))
	                    .build()
	                    .verify(token.replace(token_prefix, ""))
	                    .getSubject();
	        
	            if (user != null) {
	                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	            }
	            return null;
	        }
	        return null;
	    }
}
