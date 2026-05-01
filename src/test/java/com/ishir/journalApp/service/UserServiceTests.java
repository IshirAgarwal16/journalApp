package com.ishir.journalApp.service;

import com.ishir.journalApp.entity.User;
import com.ishir.journalApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTests {

    @MockBean
    private UserRepository userRepository;

    // ✅ ADD THIS (IMPORTANT)
    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @BeforeEach
    void setup() {
        // Mock save
        when(userRepository.save(any(User.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // Mock find
        when(userRepository.findByUserName(anyString()))
                .thenReturn(null);

        // ✅ Mock password encoding (IMPORTANT FIX)
        when(passwordEncoder.encode(anyString()))
                .thenReturn("encoded_password");
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentProvider.class)
    public void testSaveNewName(User user){
        assertTrue(userService.saveNewUser(user));
    }
}