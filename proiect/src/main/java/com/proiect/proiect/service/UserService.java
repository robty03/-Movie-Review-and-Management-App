package com.proiect.proiect.service;

import com.proiect.proiect.model.Rol;
import com.proiect.proiect.model.Utilizator;
import com.proiect.proiect.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Clasa pentru UserService
 * @author Vile Robert-Andrei
 * @version 12 Ianuarie 2025
 */

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilizator findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilizator utilizator = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return User.builder()
                .username(utilizator.getUsername())
                .password(utilizator.getPassword())
                .roles(utilizator.getRol().name())
                .build();
    }

    public List<Utilizator> getAllUsers() {
        return userRepository.findByRolNot(Rol.ADMIN);
    }

    public void registerUser(Utilizator utilizator) {
        if (userRepository.existsByUsername(utilizator.getUsername())) {
            throw new IllegalArgumentException("Username-ul este deja folosit!");
        }
        utilizator.setRol(Rol.USER);
        utilizator.setPassword(passwordEncoder.encode(utilizator.getPassword()));
        userRepository.save(utilizator);
    }

    public Optional<Utilizator> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

}



