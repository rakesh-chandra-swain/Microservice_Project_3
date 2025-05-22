package com.nt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class AppConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .sessionManagement(session -> session
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/api/**").authenticated()
	            .anyRequest().permitAll())
	        .csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	        .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class);
	        

	    return http.build();
	}

	  

	    @Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        // Configure CORS settings here
	        return request -> {
	            var config = new org.springframework.web.cors.CorsConfiguration();
	            config.addAllowedOrigin("*"); // Allow all origins
	            config.addAllowedMethod("*"); // Allow all methods (GET, POST, etc.)
	            config.addAllowedHeader("*"); // Allow all headers
	            config.setAllowCredentials(true);
	            return config;
	        };
	}
    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }

}
