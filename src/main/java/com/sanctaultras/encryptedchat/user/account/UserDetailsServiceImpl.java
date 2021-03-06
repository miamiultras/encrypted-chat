package com.sanctaultras.encryptedchat.user.account;

import com.sanctaultras.encryptedchat.user.User;
import com.sanctaultras.encryptedchat.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findOneByEmail(username).map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public org.springframework.security.core.userdetails.User createUserDetails(User user) {
        return new CustomSessionUser(user.getEmail(),user.getPassword(), Collections.emptyList()
        , user.getId());
    }

}