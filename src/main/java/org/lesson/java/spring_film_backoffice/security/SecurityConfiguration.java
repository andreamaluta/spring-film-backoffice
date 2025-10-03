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

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> requests
                .requestMatchers("/css/**", "/js/**", "/images/**", "/webjars/**", "/style.css").permitAll()
                .requestMatchers("/films/create", "/films/edit/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/films/**").hasAuthority("ADMIN")
                .requestMatchers("/genres", "/genres/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/genres/**").hasAuthority("ADMIN")
                .requestMatchers("/actors", "/actors/**").hasAuthority("ADMIN")
                .requestMatchers(HttpMethod.POST, "/actors/**").hasAuthority("ADMIN")
                .requestMatchers("/films", "/films/**").hasAnyAuthority("ADMIN")
                .requestMatchers("/").hasAnyAuthority("ADMIN", "USER"))
                .formLogin(Customizer.withDefaults());
        return http.build();
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
