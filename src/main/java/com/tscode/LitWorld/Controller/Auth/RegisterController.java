package com.tscode.LitWorld.Controller.Auth;

import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Dto.SignUpDto;
import com.tscode.LitWorld.exception.DuplicateUsernameException;
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
    public String save( @Validated @ModelAttribute("sv") SignUpDto sv, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng sửa các lỗi sau:");
            return "/auth/register";
        } else {
            try {
                querryUser.adduser(sv);
            } catch (RuntimeException e) {
                model.addAttribute("message", e.getMessage());
                System.out.println(e.getMessage());
                return "/auth/register";
            }
            return "redirect:/login";
        }
    }

}
