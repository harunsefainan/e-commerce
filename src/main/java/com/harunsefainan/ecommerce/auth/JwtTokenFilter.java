package com.harunsefainan.ecommerce.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.sql.results.graph.tuple.TupleResultAssembler;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * This filter intercepts incoming HTTP requests to validate and authenticate JWT tokens.
 */
@Component
@lombok.AllArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private JwtTokenManager jwtTokenManager;

    /**
     *
     We override the doFilterInternal method to handle each incoming HTTP request.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = httpServletRequest.getHeader("Authorization");
        String username = null;
        String token = null;

        //If there is an Authorization header and it contains the word "Bearer," we extract
        //the token value and assign it to the token variable.
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            try {
                username = jwtTokenManager.getUsernameToken(token);//We extract the username from the token using the jwtTokenManager.getUsernameToken(token) method.
            } catch (Exception e) {
                System.out.println(e.getMessage());//If an error occurs during this process, we print the error message to the console.
            }
        }
        //If the token is valid, we create a UsernamePasswordAuthenticationToken with the username and other details,
        //and add it to the security context (SecurityContextHolder).This step authenticates and authorizes the user.
        if (username != null && token != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (jwtTokenManager.tokenValidate(token)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
