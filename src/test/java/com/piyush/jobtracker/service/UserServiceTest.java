package com.piyush.jobtracker.service;

import com.piyush.jobtracker.dto.UserResponseDTO;
import com.piyush.jobtracker.entity.User;
import com.piyush.jobtracker.exception.UserNotFoundException;
import com.piyush.jobtracker.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void saveUser_shouldReturnUserResponseDTO_withHashedPassword() {

        // ARRANGE — test ka data aur fake behavior set up karo
        User inputUser = new User();
        inputUser.setName("Piyush");
        inputUser.setEmail("piyush@example.com");
        inputUser.setPassword("plainPassword123");
        inputUser.setCollege("RNSIT");
        inputUser.setYearOfPassing(2027);

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setName("Piyush");
        savedUser.setEmail("piyush@example.com");
        savedUser.setPassword("hashedPassword");
        savedUser.setCollege("RNSIT");
        savedUser.setYearOfPassing(2027);

        when(passwordEncoder.encode("plainPassword123")).thenReturn("hashedPassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // ACT — asli method call karo jo test kar rahe ho
        UserResponseDTO result = userService.saveUser(inputUser);

        // ASSERT — verify karo result sahi hai
        assertEquals(1L, result.getId());
        assertEquals("Piyush", result.getName());
        assertEquals("piyush@example.com", result.getEmail());
        assertEquals("hashedPassword",inputUser.getPassword());
        verify(userRepository).save(inputUser);
    }
    @Test
    void getUserById_shouldThrowException_whenUserNotFound(){

        when(userRepository.findById(99L))
                .thenReturn(Optional.empty());
        assertThrows(
                UserNotFoundException.class,
                () -> userService.getUserById(99L)
        );
    }



}