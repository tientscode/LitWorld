package com.tscode.LitWorld.Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserClassDto {
    private String name;
    private String account;
    private String password;
    private String email;


    public UserClassDto(String name, String account, String password) {
    }
}
