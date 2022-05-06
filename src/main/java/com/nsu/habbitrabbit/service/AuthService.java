package com.nsu.habbitrabbit.service;

import com.nsu.habbitrabbit.domain.Player;
import com.nsu.habbitrabbit.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final PlayerRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(PlayerRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Player findByEmail(String email) {
        return userRepository.findPlayerByEmail(email);
    }
    public Player findByEmailAndPassword(String email, String password) {
        Player userCredential = findByEmail(email);
        if (userCredential != null) {
            if (passwordEncoder.matches(password, userCredential.getPassword())) {
                return userCredential;
            }
        }
        return null;
    }
}
