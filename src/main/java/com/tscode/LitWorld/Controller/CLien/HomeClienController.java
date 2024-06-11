package com.tscode.LitWorld.Controller.CLien;

import com.tscode.LitWorld.Database.CategoryClass.CategoryClass;
import com.tscode.LitWorld.Database.CategoryClass.CategoryRepository;
import com.tscode.LitWorld.Database.RoleClass.RoleClass;
import com.tscode.LitWorld.Database.StoryClass.QuerryStory;
import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.StoryRepository;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Service.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.catalina.realm.UserDatabaseRealm.getRoles;


@Controller
public class HomeClienController {

    @Autowired
    SessionService session;
    @Autowired
    QuerryStory querryStory;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StoryRepository storyRepository;

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

        return "component/ClienComponets/list";
    }

    @PostMapping("/home/gio-hang")
    public String giohanga(@RequestBody List<String> userCart) {
        System.out.println("nó đây nè" + userCart);

        List<StoryClass> stories = new ArrayList<>(); // Tạo một danh sách mới để lưu các truyện

        for (String id : userCart) {
            Long storyId = Long.parseLong(id);
            StoryClass story = storyRepository.findById(storyId).orElse(null);
            if (story != null) {
                System.out.println("Truyện: " + story.getName());
                stories.add(story); // Thêm truyện vào danh sách
            }
        }

        session.set("lists", stories); // Lưu danh sách truyện vào session

        return "component/ClienComponets/tutruyen";
    }


    @RequestMapping("/home/gio-hang")
    public String giohang(){
        return "component/ClienComponets/tutruyen";
    }

    @RequestMapping("/home/user")
    public String user(){
        return "component/ClienComponets/user";
    }



    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        // Thêm danh sách câu chuyện vào model
        UserClass user =  session.get("user");
        List<StoryClass> list = querryStory.getlistStory();
        model.addAttribute("liststory", list);
//        Set<String> roles = user.getRoles().stream()
//                .map(RoleClass::getName)
//                .collect(Collectors.toSet());
//        if (roles.contains("Role_Admin")) {
//           model.addAttribute("isAdmin", true);
//        } else {
//            model.addAttribute("isAdmin", false);
//        }
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        List<CategoryClass> list = categoryRepository.findAll();
        model.addAttribute("Categorylist", list);
    }
}
