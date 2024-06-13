package com.tscode.LitWorld.Controller.CLien;

import com.tscode.LitWorld.Database.CategoryClass.CategoryClass;
import com.tscode.LitWorld.Database.CategoryClass.CategoryRepository;
import com.tscode.LitWorld.Database.RoleClass.RoleClass;
import com.tscode.LitWorld.Database.StoryClass.QuerryStory;
import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.StoryRepository;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Database.UserClass.UserRepository;
import com.tscode.LitWorld.Dto.UserClassDto;
import com.tscode.LitWorld.Service.SessionService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String giohanga(@RequestBody List<String> userCart,Model model) {
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
    public String giohang(){
        return "component/ClienComponets/tutruyen";
    }

    @RequestMapping("/home/user")
    public String user(){
        return "component/ClienComponets/user";
    }

    @PostMapping(value = "/home/user", consumes = "multipart/form-data")
    public String updateUser(@ModelAttribute UserClass userClass,
                             @RequestParam("image") MultipartFile imageFile,
                             Model model) {
        try {
//            if (!imageFile.isEmpty()) {
//                // Lấy tên file
//                String fileName = imageFile.getOriginalFilename();
//                // Lưu file vào thư mục (ví dụ: /uploads/)
//                Path path = Paths.get("uploads/" + fileName);
//                Files.copy(imageFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//                // Lưu tên file vào thuộc tính image của UserClass
//                userClass.setImage(fileName);
//            }

            // Gọi phương thức dịch vụ để cập nhật thông tin người dùng
            querryUser.upClassUser(userClass.getId(), userClass);

            model.addAttribute("successMessage", "Thông tin đã được cập nhật thành công");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật thông tin");
        }

        return "component/ClienComponets/user";
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
