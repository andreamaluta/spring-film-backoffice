package org.lesson.java.spring_film_backoffice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/style.css").permitAll()
                        .requestMatchers("/films/create", "/films/edit/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/films/**").hasAuthority("ADMIN")
                        .requestMatchers("/genres", "/genres/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/genres/**").hasAuthority("ADMIN")
                        .requestMatchers("/actors", "/actors/**").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/actors/**").hasAuthority("ADMIN")
                        .requestMatchers("/films", "/films/**").hasAnyAuthority("ADMIN")
                        .requestMatchers("/").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers("/api/films", "/api/films/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> {
                }) // equivalente a Customizer.withDefaults()
                .build();
    }

    // bean di cors
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("http://localhost:5173")
                        .allowedMethods("*");
            }
        };
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        // servizio per il recupero degli utenti
        authProvider.setUserDetailsService(userDetailService());

        // password encoder che uso
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
