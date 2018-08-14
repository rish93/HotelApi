//package com.mashup;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.crypto.SecretKey;
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.auth0.jwt.JWT;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mashup.hotel.dao.ApplicationUserRepository;
//import com.mashup.hotel.model.ApplicationUser;
//import com.mashup.hotel.service.UserDetailServiceImpl;
//
//import ch.qos.logback.core.net.SyslogOutputStream;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.impl.crypto.MacProvider;
//
//import com.auth0.jwt.algorithms.Algorithm;
//
//
//@Component
//public class JwtAuthenticationFilter extends OncePerRequestFilter  {
//
//    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
//
//	//@Value("${security.secret-key}")
//	private String secret="secret";
//
////	@Value("${security.token-prefix}")
//	private String token_prefix="Bearer";
//
//	//@Value("${security.expiration-time}")
//	private String expiration_time="864_000_000";
//
//	
//	//@Value("${security.header-string}")
//	private String header_string="Authorization";
//
////	    @Autowired
////	    private UserDetailServiceImpl userDetailsService;
////	    @Autowired
////	    private JwtTokenProvider tokenProvider;
//
//	private AuthenticationManager authenticationManager;
//
//	@Autowired
//	ApplicationUserRepository appRep;
////    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
////        this.authenticationManager = authenticationManager;
////    }
//
////    @Override
////    public Authentication attemptAuthentication(HttpServletRequest req,
////                                                HttpServletResponse res) throws AuthenticationException {
////        try {
////            ApplicationUser creds = new ObjectMapper()
////                    .readValue(req.getInputStream(), ApplicationUser.class);
////
////            return authenticationManager.authenticate(
////                    new UsernamePasswordAuthenticationToken(
////                            creds.getUsername(),
////                            creds.getPassword(),
////                            new ArrayList<>())
////            );
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
////    }
////
////    @Override
////    protected void successfulAuthentication (HttpServletRequest req,
////                                             HttpServletResponse res,
////                                             FilterChain chain,
////                                             Authentication auth) throws IOException, ServletException {
////        SecretKey key = MacProvider.generateKey(SignatureAlgorithm.HS512);
////        byte[] keyBytes = key.getEncoded();
////        System.out.println(keyBytes.toString());
////
////        String token = Jwts.builder()
////                    .setSubject(((User) auth.getPrincipal()).getUsername())
////                    .setExpiration(new Date(System.currentTimeMillis() + expiration_time))
////                    .signWith(SignatureAlgorithm.HS512, keyBytes)
////                    .compact();
////
////            res.addHeader(header_string, token_prefix + token);
////    }
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		
//		 try {
//	            String jwt = getJwtFromRequest(request);
//	            System.out.println(jwt);
////System.out.println(tokenProvider.validateToken(jwt));
//System.out.println(StringUtils.hasText(jwt));
//JwtTokenProvider tokenProvider= new JwtTokenProvider();
//UserDetailServiceImpl userDetailsService= new UserDetailServiceImpl(appRep);
//
//	            if (StringUtils.hasText(jwt) ) {//&& tokenProvider.validateToken(jwt)
//	                Long userId = tokenProvider.getUserIdFromJWT(jwt);
//
//	                UserDetails userDetails = userDetailsService.loadUserById(userId);
//	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//	                SecurityContextHolder.getContext().setAuthentication(authentication);
//	            }
//	        } catch (Exception ex) {
//	            logger.error("Could not set user authentication in security context", ex);
//	        }
//	        filterChain.doFilter(request, response);
//
//	}
//	 private String getJwtFromRequest(HttpServletRequest request) {
//	        String bearerToken = request.getHeader("Authorization");
//	        System.out.println(bearerToken.startsWith("Bearer "));
//	        System.out.println(StringUtils.hasText(bearerToken));
//	        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
//	           
//	        	return bearerToken.substring(7, bearerToken.length()).trim();
//	        }
//	        return null;
//	    }
//    
////    @Override
////    protected void successfulAuthentication(HttpServletRequest req,
////                                            HttpServletResponse res,
////                                            FilterChain chain,
////                                            Authentication auth) throws IOException, ServletException {
////
////        String token = JWT.create()
////                .withSubject(((User) auth.getPrincipal()).getUsername())
////                .withExpiresAt(new Date(System.currentTimeMillis() + Integer.valueOf(expiration_time)))
////                .sign(Algorithm.HMAC512(secret.getBytes()));
////        res.addHeader(header_string, token_prefix + token);
////    }
//
//}
