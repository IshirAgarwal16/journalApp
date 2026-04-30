package com.ishir.journalApp.service;

import com.ishir.journalApp.entity.User;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) {

        return Stream.of(
                Arguments.of(new User("shyam", "shyam")),
                Arguments.of(new User("suraj", "suraj"))
        );
    }
}