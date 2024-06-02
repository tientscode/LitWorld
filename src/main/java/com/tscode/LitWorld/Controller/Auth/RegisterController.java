package com.tscode.LitWorld.Controller.Auth;

import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Dto.SignUpDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegisterController {
    @Autowired
    private QuerryUser querryUser;

    @GetMapping("/register")
    public String register(@ModelAttribute("sv") SignUpDto sv) {
        return "auth/register";
    }
    @RequestMapping("/register/save")
    public String save(@Validated @ModelAttribute("sv") SignUpDto sv, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau:");
        } else {
            model.addAttribute("message", "Chúc mừng, bạn đã nhập đúng");
        }
        return "redirect:/register";
    }
//    @PostMapping("/register")
//    public String register(Model model,HttpServletResponse response) {
//        // Kiểm tra xem mật khẩu và xác nhận mật khẩu có khớp nhau không
//        if (!registerPass.equals(registerRepass)) {
//            model.addAttribute("error", "Mật khẩu và xác nhận mật khẩu không khớp!");
//            return "auth/register"; // Trả về trang đăng ký nếu có lỗi
//        }
//
//        // Tạo đối tượng SignUpDto từ thông tin trong form
//        SignUpDto signUpDto = new SignUpDto();
//        signUpDto.setAccount(registerUsername);
//        signUpDto.setPassword(registerPass);
//        signUpDto.setEmail(registerEmail);
//        signUpDto.setName(registerName);
//
//        try {
//            // Gọi phương thức adduser để thêm người dùng mới
//           querryUser.adduser(signUpDto);
//            // Đăng ký thành công, tạo cookie và chuyển hướng đến trang login hoặc home
//            Cookie cookie = new Cookie("username", registerUsername);
//            cookie.setMaxAge(24 * 60 * 60); // Thời gian tồn tại của cookie (ví dụ: 1 ngày)
//            response.addCookie(cookie);
//
//            return "redirect:/login"; // Chuyển hướng đến trang login hoặc home sau khi đăng ký thành công
//        } catch (RuntimeException e) {
//            model.addAttribute("error", e.getMessage());
//            return "auth/register"; // Trả về trang đăng ký nếu có lỗi
//        }
//    }
}
