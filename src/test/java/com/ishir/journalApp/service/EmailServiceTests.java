package com.ishir.journalApp.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailServiceTests {

    @Autowired
    private EmailService emailService;

    @Test
    void testSendMail(){
        emailService.sendEmail("ishir.agarwal2006@gmail.com",
                "Testing Java mail sender",
                "Hi, aap kaise hain ?");
    }

}
