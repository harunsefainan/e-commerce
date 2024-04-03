package com.harunsefainan.ecommerce.auth;

import com.harunsefainan.ecommerce.entities.UserEntity;
import com.harunsefainan.ecommerce.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * This class implements the UserDetailsService interface to provide user authentication functionality.
 */
@Service
@lombok.RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username); //Retrieves user information from the database based on the username.
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found with username: " + username); //Checks if the user exists and throws a UsernameNotFoundException if not found.
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password(userEntity.getPassword())
                .roles(userEntity.getRole().toString()) //If saved as a user role (enum)
                .build();
    }
}
