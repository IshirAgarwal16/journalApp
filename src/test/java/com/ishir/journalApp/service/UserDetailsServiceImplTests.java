package com.ishir.journalApp.service;

import com.ishir.journalApp.UserDetailsServiceImpl;
import com.ishir.journalApp.entity.User;
import com.ishir.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

import static org.mockito.Mockito.*;

@ActiveProfiles("dev")
public class UserDetailsServiceImplTests {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsernameTest(){

        User mockUser = new User("ram", "inrinrick");
        mockUser.setRoles(Arrays.asList("USER"));

        when(userRepository.findByUserName(anyString()))
                .thenReturn(mockUser);

        UserDetails user = userDetailsService.loadUserByUsername("ram");

        Assertions.assertNotNull(user);
    }
}