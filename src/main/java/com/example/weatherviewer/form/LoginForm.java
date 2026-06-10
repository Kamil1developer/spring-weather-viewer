package com.example.weatherviewer.form;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginForm {
    private final String username;
    private final String password;
}
