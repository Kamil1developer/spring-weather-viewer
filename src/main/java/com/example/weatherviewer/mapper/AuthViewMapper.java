package com.example.weatherviewer.mapper;

import com.example.weatherviewer.auth.AuthResult;
import com.example.weatherviewer.messages.AuthMessage;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthViewMapper {
    private final AuthResult authResult;

    public Optional<String> resolveMessage(AuthResult currentResult){
        switch (currentResult){
            case INVALID_PASSWORD -> {
                return Optional.of(AuthMessage.INVALID_PASSWORD.getMessage());
            }
            case INVALID_LOGIN -> {
                return Optional.of(AuthMessage.USER_NOT_FOUND.getMessage());
            }
            case CONFIRM_PASSWORD_INVALID -> {
                return Optional.of(AuthMessage.CONFIRM_PASSWORD_INVALID.getMessage());
            }

        }
        return Optional.empty();
    }


}
