package com.example.weatherviewer.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotBlank(message = "Логин не должен быть пустым")
    private  String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    private  String password;
}
