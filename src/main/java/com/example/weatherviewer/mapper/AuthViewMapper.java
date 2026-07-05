package com.example.weatherviewer.mapper;

import com.example.weatherviewer.auth.AuthResult;
import com.example.weatherviewer.messages.AuthMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

import java.util.Optional;

@RequiredArgsConstructor
public class AuthViewMapper {
    private final AuthResult authResult;

    public void applyErrors(AuthResult currentResult, BindingResult bindingResult){
        switch (currentResult){
            case INVALID_PASSWORD, INVALID_LOGIN -> {
                bindingResult.reject(null,"Invalid username or password");
            }
            case CONFIRM_PASSWORD_INVALID -> {
                bindingResult.reject(null,"Пароли не совпадают");
            }

        }
    }


}
