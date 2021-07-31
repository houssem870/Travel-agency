package com.ditraacademy.travel_agency.core.user.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignInRequest {
    private String username;
    private String password;
}
