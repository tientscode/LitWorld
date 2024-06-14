package com.tscode.LitWorld.Controller.CLien;

import com.tscode.LitWorld.Database.CategoryClass.CategoryClass;
import com.tscode.LitWorld.Database.CategoryClass.CategoryRepository;
import com.tscode.LitWorld.Database.StoryClass.QuerryStory;
import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.StoryRepository;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Database.UserClass.UserRepository;
import com.tscode.LitWorld.Service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;


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
    @Autowired
    QuerryUser querryUser;
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        return "component/ClienComponets/list";
    }

    @PostMapping("/home/gio-hang")
    public String giohanga(@RequestBody List<String> userCart, Model model) {
        System.out.println("nó đây nè" + userCart);

        List<StoryClass> stories = new ArrayList<>(); // Tạo một danh sách mới để lưu các truyện
        double totalPrice = 0.0;
        for (String id : userCart) {
            Long storyId = Long.parseLong(id);
            StoryClass story = storyRepository.findById(storyId).orElse(null);
            if (story != null) {
                System.out.println("Truyện: " + story.getPrice());
                stories.add(story); // Thêm truyện vào danh sách
                totalPrice += story.getPrice();
            }
        }
        session.set("lists", stories); // Lưu danh sách truyện vào session
        session.set("totalPrice", totalPrice);
        return "component/ClienComponets/tutruyen";
    }


    @RequestMapping("/home/gio-hang")
    public String giohang() {
        return "component/ClienComponets/tutruyen";
    }

    @RequestMapping("/home/user")
    public String user() {
        return "component/ClienComponets/User";
    }

    @RequestMapping("/home/user/update")
    public String userupdate(@ModelAttribute UserClass user, BindingResult result) {
        if (result.hasErrors()) {
            return "Error";
        }
        String fileName = StringUtils.cleanPath(user.getImageFile().getOriginalFilename());
        user.setImage(fileName);
        System.out.println("bị lỗi lol gif đaay"+user);
        userRepository.save(user);
        session.set("user",user);
        return "component/ClienComponets/User";
    }


    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        // Thêm danh sách câu chuyện vào model
        List<StoryClass> list = querryStory.getlistStory();
        model.addAttribute("liststory", list);
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        List<CategoryClass> list = categoryRepository.findAll();
        model.addAttribute("Categorylist", list);
    }
}
