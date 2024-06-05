package com.tscode.LitWorld.Controller.Admin;

import com.tscode.LitWorld.Database.RoleClass.RoleClass;
import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.khaibaohamStory;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeAdminController {


    @Autowired
    private khaibaohamStory khaibaohamStory;
    @Autowired
    private QuerryUser querryUser;

    @RequestMapping("/dashboard")
    public String dashboard(Model model, HttpServletRequest request) {


        List<StoryClass> list = khaibaohamStory.getlistStory();
//        System.out.println(list);

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    UserClass user = querryUser.findByAccount(username);
                    for (RoleClass role : user.getRoles()) {
                        System.out.println(role.getName());
                        try {
                            if (role.getName().contains("Role_Admin")) {

                                model.addAttribute("user", user);
                                model.addAttribute("liststory", list);
                                return "component/AdminComponets/home";
                            } else {
                                System.out.println("lỗi ở đây nè");
                                return "error";
                            }
                        } catch (Exception e) {
                            System.out.println("đừng nói là ở đây nhé");
                            return "error";
                        }
                    }

                }
            }

        }
        return "auth/login";
    }


}