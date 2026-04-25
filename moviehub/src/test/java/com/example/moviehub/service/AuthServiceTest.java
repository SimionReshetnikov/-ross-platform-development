package com.example.moviehub.service;

import com.example.moviehub.model.User;
import com.example.moviehub.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @Test
    void register_shouldSaveUser_whenEmailUnique() {
        User user = new User();
        user.setEmail("test@mail.com");

        when(userRepository.findByEmail("test@mail.com"))
                .thenReturn(Optional.empty());

        authService.register(user);
        verify(userRepository).save(user);
    }

    @Test
    void register_shouldThrowException_whenEmailExists() {
        User user = new User();
        user.setEmail("test@mail.com");

        when(userRepository.findByEmail("test@mail.com"))
                .thenReturn(Optional.of(new User()));

        assertThrows(IllegalArgumentException.class,
                () -> authService.register(user));
    }

    @Test
    void login_shouldReturnTrue_whenCorrectData() {
        User user = new User();
        user.setEmail("test@mail.com");
        user.setPassword("123456");

        when(userRepository.findByEmail("test@mail.com"))
                .thenReturn(Optional.of(user));

        boolean result = authService.login("test@mail.com", "123456");
        assertTrue(result);
    }

    @Test
    void login_shouldReturnFalse_whenWrongPassword() {
        User user = new User();
        user.setPassword("123456");

        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.of(user));

        boolean result = authService.login("test@mail.com", "wrong");
        assertFalse(result);
    }

    @Test
    void login_shouldReturnFalse_whenUserNotFound() {
        when(userRepository.findByEmail(anyString()))
                .thenReturn(Optional.empty());

        boolean result = authService.login("test@mail.com", "123456");
        assertFalse(result);
    }
}
