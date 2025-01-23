package com.informatics.medical_record_spring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Publicly accessible routes
                        .requestMatchers(
                                "/register",
                                "/login",
                                "/",
                                "/index",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // Allow any doctor-related operations without authentication
                        .requestMatchers(HttpMethod.GET, "/api/doctors/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/doctors/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/doctors/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/doctors/**").permitAll()

                        // Admin-related routes
                        .requestMatchers("/api/**").hasRole("Admin")
                        .requestMatchers("/**").hasAnyRole("Admin","Doctor")
                        // Patient-related routes
                        .requestMatchers("/patients/**").hasAnyRole("Patient","Doctor")

                        .requestMatchers("/diagnoses/**").hasRole("Doctor")
                        .requestMatchers("/search/**").hasRole("Doctor")
                        .requestMatchers("/visits/**").hasRole("Doctor")
                        .requestMatchers("/sick-leaves/**").hasRole("Doctor")
                        .requestMatchers("/visits/**").hasRole("Doctor")




                        // Secure all other requests
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/", true) // Redirect after successful login
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Logout redirection
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return rawPassword.toString();
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return rawPassword.toString().equals(encodedPassword);
            }
        };
    }
}
