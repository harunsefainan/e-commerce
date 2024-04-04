package com.harunsefainan.ecommerce.config;

import com.harunsefainan.ecommerce.auth.JwtTokenFilter;
import com.harunsefainan.ecommerce.auth.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@lombok.AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfiguration {

    private UserDetailsServiceImpl userDetailsService;

    private JwtTokenFilter jwtTokenFilter;

    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(Customizer.withDefaults())
                .authorizeRequests()
                .requestMatchers("/authenticate").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/seller/**").hasRole("SELLER")
                .requestMatchers("/customer/**").hasRole("CUSTOMER")
                .requestMatchers("/dataanalyst/**").hasRole("DATA_ANALYST")
                .anyRequest().authenticated();
        http.sessionManagement(Customizer.withDefaults());
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

