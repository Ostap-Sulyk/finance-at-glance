package com.financeatglance.financeatglance.security;

import com.financeatglance.financeatglance.constants.SecurityConstants;
import com.financeatglance.financeatglance.security.filter.AuthenticationFilter;
import com.financeatglance.financeatglance.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {
    private final CustomAuthenticationManager customAuthenticationManager;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authFilter = new AuthenticationFilter(customAuthenticationManager);

        http.headers().frameOptions().disable()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()  // use authorizeRequests instead of authorizeHttpRequests
                .requestMatchers(PathRequest.toH2Console()).permitAll()  // antMatchers is available under authorizeRequests
                .requestMatchers(HttpMethod.POST, SecurityConstants.REGISTER_PATH).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(authFilter)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }



}
