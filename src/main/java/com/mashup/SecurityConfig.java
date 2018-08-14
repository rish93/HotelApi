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
//@EntityScan("com.mashup.security.model") 
//@ComponentScan(basePackages= {"com.mashup.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Value("${security.sign-up-url}")
	private String sign_up_url="/login/sign-up";
	
		@Autowired
	   private UserDetailServiceImpl userDetailsService;
	   private BCryptPasswordEncoder bCryptPasswordEncoder;

	   @Autowired
	    private JwtAuthenticationEntryPoint unauthorizedHandler;

//	   @Bean
//	    public JWTAuthenticationFilter jwtAuthenticationFilter() {
//	        return new JWTAuthenticationFilter();
//	    }
	    
	   public SecurityConfig(UserDetailServiceImpl userDetailsService, 
			    BCryptPasswordEncoder bCryptPasswordEncoder) {
	        		this.userDetailsService = userDetailsService;
	        		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	        		System.out.println("------------------ " +sign_up_url+" ------------------");
	    }
	    
//	@Bean
//     public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//     }
	 
	
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http.cors().and().csrf().disable().exceptionHandling()
	        .authenticationEntryPoint(unauthorizedHandler)
	        .and().authorizeRequests()
	               
	                .antMatchers(HttpMethod.POST, "/login/signin", "/login/signup").permitAll()
	                .antMatchers("/guest/checkIn").permitAll()
	                .anyRequest().authenticated()
	                .and()
	             //   .addFilter(new JWTAuthenticationFilter(authenticationManager()))
	               
	                .addFilterAfter(new JwtAuthorizationFilter(authenticationManager()), UsernamePasswordAuthenticationFilter.class)
	                .authorizeRequests()
	                .antMatchers("/admin/")
	                .authenticated().and()
	                // .addFilterAfter(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	                // this disables session creation on Spring Security
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	        //
	      //  http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
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


//import org.springframework.context.annotation.Bean;

//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;

//@Configuration
//@EnableWebSecurity
//public class LoginApplicationConfig extends WebSecurityConfigurerAdapter{

	
	//added two user  one with role user ie. USER
	//second with role admin and user ie. ADMIN
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
//		 authenticationMgr.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//		.withUser("GUEST").password("GUEST").authorities("ROLE_USER").
//		 and()
//		.withUser("ADMIN").password("ADMIN").authorities("ROLE_USER","ROLE_ADMIN");
//	}
	
//	@Override
//	protected void configure(HttpSecurity http)throws Exception {
//		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
//		
		//		.antMatchers("/")
		//		.access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
		//		.antMatchers("/guest").access("hasRole('ROLE_USER')")
		//		.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
		//		.and()
		//.formLogin().loginPage("adminLogin.html")
		//.defaultSuccessUrl("adminTracker")
		//.failureUrl("error")
		//.usernameParameter("username").passwordParameter("password")
		//.and()
		//.logout().logoutSuccessUrl("/adminLogin.html");
//		
//	}
	
//
//	@SuppressWarnings("deprecation")
//	@Bean
//	public static NoOpPasswordEncoder passwordEncoder() {
//	return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests().antMatchers("/**")
//		.hasRole("USER")
//		.and()
//		.formLogin();
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//		.withUser("user")
//		.password("password")
//		.roles("USER")
//		.and()
//		.withUser("admin")
//		.password("password")
//		.roles("ADMIN", "USER");
//	}


//}
