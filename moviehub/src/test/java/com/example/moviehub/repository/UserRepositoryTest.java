package com.example.moviehub.repository;

import com.example.moviehub.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void findByEmail_shouldReturnUser_whenEmailExists() {
        User user = new User();
        user.setName("Test");
        user.setEmail("test@mail.com");
        user.setPassword("123456");

        userRepository.save(user);

        Optional<User> foundUser = userRepository.findByEmail("test@mail.com");

        assertTrue(foundUser.isPresent());
        assertEquals("test@mail.com", foundUser.get().getEmail());
    }

    @Test
    void findByEmail_shouldReturnEmpty_whenEmailDoesNotExist() {
        Optional<User> foundUser = userRepository.findByEmail("unknown@mail.com");

        assertTrue(foundUser.isEmpty());
    }
}
