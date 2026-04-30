package com.ishir.journalApp.service;

import com.ishir.journalApp.entity.User;
import com.ishir.journalApp.repository.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewName(User user){

        // 👉 Mock behavior
        when(userRepository.save(any(User.class))).thenReturn(user);

        // 👉 Call method
        boolean result = userService.saveNewUser(user);

        // 👉 Assert
        assertTrue(result);

        // 👉 Verify call
        verify(userRepository, times(1)).save(user);
    }
}