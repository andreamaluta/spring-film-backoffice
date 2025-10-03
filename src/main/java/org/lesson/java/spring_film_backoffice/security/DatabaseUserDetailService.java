package org.lesson.java.spring_film_backoffice.security;

import java.util.Optional;

import org.lesson.java.spring_film_backoffice.model.User;
import org.lesson.java.spring_film_backoffice.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = userRepository.findByUsername(username);

        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("There are no user available with username" + username);
        }

        return new DatabaseUserDetails(userAttempt.get());

    }

}
