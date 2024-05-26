package com.tscode.LitWorld.Controller.CLien;

import com.tscode.LitWorld.Database.StoryClass.QuerryStory;
import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class HomeClienController {

    @Autowired
    private QuerryUser querryUser;

    @Autowired
    private QuerryStory querryStory;

//    @RequestMapping("/home")
//    public String home(Model model, HttpServletRequest request) {
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("username")) {
//                    String username = cookie.getValue();
//                    UserClass user = querryUser.findByAccount(username);
//                    model.addAttribute("mess", "Đăng nhập thành công!");
//                    model.addAttribute("user", user);
////                    System.out.println("Đã đọc cookie username: " + username);
//                    break;
//                }
//            }
//        }
//        return "index";
//    }

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        // Bất kỳ xử lý nào khác mà bạn muốn thêm vào trong phương thức `home`
//        model.addAttribute("extra", "Any additional processing or attributes can be added here");

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



    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        // Thêm danh sách câu chuyện vào model
        List<StoryClass> list = querryStory.getlistStory();
        model.addAttribute("liststory", list);

        // Kiểm tra cookie và thêm thông tin người dùng nếu có
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    UserClass user = querryUser.findByAccount(username);
                    model.addAttribute("mess", "Đăng nhập thành công!");
                    model.addAttribute("user", user);
                    break;
                }
            }
        }
    }


}
