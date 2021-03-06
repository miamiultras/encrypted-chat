package com.sanctaultras.encryptedchat.user.account.register;

import com.sanctaultras.encryptedchat.user.User;
import com.sanctaultras.encryptedchat.user.account.UserDetailsServiceImpl;
import com.sanctaultras.encryptedchat.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final UserDetailsServiceImpl userDetailsService;

    public RegisterService(PasswordEncoder passwordEncoder, UserRepository userRepository, UserDetailsServiceImpl userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
    }

    public org.springframework.security.core.userdetails.User register(RegisterForm registerForm) {
        User newUser = new User();
        newUser.setEmail(registerForm.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerForm.getPassword()));
        newUser = userRepository.save(newUser);
        return userDetailsService.createUserDetails(newUser);
    }
}
