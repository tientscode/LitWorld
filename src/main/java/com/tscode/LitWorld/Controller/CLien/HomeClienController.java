package com.tscode.LitWorld.Controller.CLien;

import com.tscode.LitWorld.Database.StoryClass.QuerryStory;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeClienController {

    @Autowired
    private QuerryUser querryUser;

    @Autowired
    private QuerryStory querryStory;

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    UserClass user = querryUser.findByAccount(username);
                    model.addAttribute("mess", "Đăng nhập thành công!");
                    model.addAttribute("user", user);
//                    System.out.println("Đã đọc cookie username: " + username);
                    break;
                }
            }
        }
        return "index";
    }

    @RequestMapping("/home/gio-hang")
    public String giohang(){
        return "giohang";
    }

    @RequestMapping("/home/user")
    public String user(){
        return "userupdate";
    }
}
