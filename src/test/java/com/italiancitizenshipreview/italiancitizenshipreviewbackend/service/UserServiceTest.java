package com.italiancitizenshipreview.italiancitizenshipreviewbackend.service;

import com.italiancitizenshipreview.italiancitizenshipreviewbackend.domain.User;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.ServiceProviderRepository;
import com.italiancitizenshipreview.italiancitizenshipreviewbackend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private PasswordEncoderService passwordEncoderService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createUser_ShouldCreateNewUser() {
        //Arrange
        String username = "XXXX";
        String password = "test";
        String role = "ROLE_USER";
        User user = new User();
        user.setUsername(username);
        user.setPassword("encodedpassword"); // Assume the password encoder service returns this specific value for testing
        user.setRoles(role);

        when(passwordEncoderService.encode(password)).thenReturn("encodedpassword");
        when(userRepository.save(any(User.class))).thenReturn(user);

        //Act
        User expectedUser = userService.createUser(username, password, role);
        //Assert
        assertEquals(user, expectedUser);
    }


    @Test
    public void existsByUsername_ShouldReturnTrue() {
        //Arrange
        String username = "XXXX";
        //Act
        when(userRepository.existsByUsername(username)).thenReturn(true);
        boolean result = userService.existsByUsername(username);
        //Assert
        assertTrue(result);
    }





    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}
