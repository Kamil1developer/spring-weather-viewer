package com.example.weatherviewer.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {
    @NotBlank(message = "Логин не должен быть пустым")
    @Pattern(
            regexp = "^[A-Za-z]+$",
            message = "Поле должно содержать только латинские буквы"
    )
    @Size(min = 3, max = 50, message = "Длина должна быть от 3 до 50 символов")
    private  String username;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 4, message = "Пароль должен быть минимум 4 символа.")
    private  String password;

    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 4, message = "Пароль должен быть минимум 4 символа.")
    private  String repeatPassword;

}
