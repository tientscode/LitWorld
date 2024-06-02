package com.tscode.LitWorld.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.*;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto {
    @NotBlank(message = "Vui lòng nhập tên")
    private String name;

    @NotBlank(message = "Vui lòng nhập mật khẩu")
    @Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
    @Pattern(regexp = "(?=.*[A-Z])(?=.*[!@#$&*]).*", message = "Mật khẩu phải chứa ít nhất một ký tự viết hoa và một ký tự đặc biệt")
    private String password;

    @NotBlank(message = "Vui lòng nhập email")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Vui lòng nhập tên tài khoản")
    private String account;

}
