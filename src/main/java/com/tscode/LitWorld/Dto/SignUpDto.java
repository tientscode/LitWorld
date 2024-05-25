package com.tscode.LitWorld.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    private String name;
    private String password;
    private String email;
    private String account;

}
