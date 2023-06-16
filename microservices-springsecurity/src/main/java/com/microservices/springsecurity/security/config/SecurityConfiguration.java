package com.microservices.springsecurity.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfiguration {

    // Configuring HttpSecurity
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Need for POST access to avoid 403 - Forbidden exception
                .csrf((csrf) -> csrf.disable());
               /* .authorizeHttpRequests((authz) -> authz
                        // .anyRequest().authenticated()
                        .requestMatchers("/api/employee/**").permitAll()
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults());*/
        return http.build();
    }

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf((csrf) -> csrf.disable());
        return http.build();
    }*/

    // Configuring WebSecurity
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/ignore1", "/ignore2");
    }

    // In-Memory Authentication
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    // Local AuthenticationManager
    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults())
                .authenticationManager(new CustomAuthenticationManager());
        return http.build();
    }*/
}
