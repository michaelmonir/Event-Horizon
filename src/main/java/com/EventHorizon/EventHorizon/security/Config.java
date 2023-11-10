package com.EventHorizon.EventHorizon.security;

import org.junit.jupiter.api.condition.EnabledIf;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class Config {
    @Bean
    public UserDetailsService inMemoryUserDetailsManager(){
        UserDetails userDetails1= User.builder().username("ahmed").password("{bcrypt}$2a$12$tEpH8Tch91e6Vvjjv4aLbe9AAPdou9XlQN1.k2pFzrZ79leGU.VT2").roles("ADMIN").build();
        return new InMemoryUserDetailsManager(userDetails1);
    }

    @Bean
    public SecurityFilterChain basicFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configure->configure.
                        requestMatchers(HttpMethod.POST,"/proxy/basicSignIn/**").permitAll()
                        .requestMatchers(HttpMethod.GET,"/proxy/go").hasRole("ADMIN")
        );
        http.httpBasic(withDefaults());
        http.csrf(c->c.disable());
        return http.build();
    }
}
