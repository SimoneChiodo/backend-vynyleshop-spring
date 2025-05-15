package com.vynyleshop.org.vynyleshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {
 
  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
    .requestMatchers("vynil", "vynil/**").hasAnyAuthority("USER", "ADMIN")
    .requestMatchers("artist", "artist/**").hasAnyAuthority("USER", "ADMIN")
    .requestMatchers("vynil/create", "vynil/edit/**", "vynil/delete/**").hasAuthority("ADMIN")
    .requestMatchers("artist/create", "artist/edit/**", "artist/delete/**").hasAuthority("ADMIN")
    .requestMatchers("/api/**").permitAll()
    .requestMatchers("/**").hasAnyAuthority("USER", "ADMIN")
    )
    .formLogin(withDefaults -> {})
    .logout(withDefaults -> {})
    .exceptionHandling(withDefaults -> {})
    .csrf(csrf -> csrf.disable()); // I'm disabling CSRF for being accessible from Postman. I'm also exposed to CSRF attacks, but this is a demo application and I don't care about it. In a production application, you should enable CSRF protection.
    
    return http.build();
  }

  @Bean
  DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    
    return authProvider;
  }

  @Bean
  DatabaseUserDetailsService userDetailsService(){
    return new DatabaseUserDetailsService();
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

}
