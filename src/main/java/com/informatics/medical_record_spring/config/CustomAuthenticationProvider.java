package com.informatics.medical_record_spring.config;

import com.informatics.medical_record_spring.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//
//        // Fetch user from the database
//        User user = userRepository.findByUsername(username)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        // Validate password
//        if (passwordEncoder.matches(password, user.getPasswordHash())) {
//            UserDetails userDetails = User.builder()
//                    .username(user.getUsername())
//                    .password(user.getPasswordHash())
//                    .roles(user.getRole().getRoleName())
//                    .build();
//
//            return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//        }
//
//        throw new RuntimeException("Invalid credentials");
//    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
