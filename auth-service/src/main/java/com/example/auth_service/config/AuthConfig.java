package com.example.auth_service.config;



import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class AuthConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

   

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
         http.csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(requests -> requests.requestMatchers("/auth/register","/auth/login","/auth/validate")
            .permitAll()
            .anyRequest()
            .authenticated()
            ).sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authenticationProvider);

            return http.build();
    }
    
}
