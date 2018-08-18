package com.mashup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.mashup.hotel.service.UserDetailServiceImpl;



@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Value("${security.sign-up-url}")
	private String sign_up_url="/login/sign-up";
	
		@Autowired
	   private UserDetailServiceImpl userDetailsService;
	   private BCryptPasswordEncoder bCryptPasswordEncoder;

	   @Autowired
	    private JwtAuthenticationEntryPoint unauthorizedHandler;

	   public SecurityConfig(UserDetailServiceImpl userDetailsService, 
			    BCryptPasswordEncoder bCryptPasswordEncoder) {
	        		this.userDetailsService = userDetailsService;
	        		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	        		System.out.println("------------------ " +sign_up_url+" ------------------");
	    }
	    
	     @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable().exceptionHandling()
	        .authenticationEntryPoint(unauthorizedHandler)
	        .and().authorizeRequests()
            .antMatchers(HttpMethod.POST, "/login/signin", "/login/signup").permitAll()
            .antMatchers("/guest/checkIn",
            		    "/actuator/info",
            		    "/actuator/health").permitAll()
            .antMatchers("/actuator/**","/admin/**").authenticated()
            .anyRequest().authenticated()
            .and()
            .addFilterAfter(new JwtAuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
            .authorizeRequests()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	    }

	    
	     @Override
	     public void configure(WebSecurity web) throws Exception {
	         web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
	     }

	    
	    @Override
	    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
	        authenticationManagerBuilder
	                .userDetailsService(userDetailsService)
	                .passwordEncoder(passwordEncoder());
	    }
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	  @Bean
	  CorsConfigurationSource corsConfigurationSource() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
	  }
	    
	  @Bean()
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
}

