package com.nag.service;

import com.nag.model.AppUser;
import com.nag.repository.AppUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @description Building user for authentication
 * @author Juha Hinkula (Kristian)
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final AppUserRepository repository;

public UserDetailsServiceImpl(AppUserRepository repository) {
    this.repository = repository;
}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = repository.findByUsername(username);

        User.UserBuilder builder = null;
        if (user.isPresent()) {
            AppUser currentUser = user.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }

        return builder.build();

    }
}
