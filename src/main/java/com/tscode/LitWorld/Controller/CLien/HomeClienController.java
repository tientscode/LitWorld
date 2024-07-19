package com.tscode.LitWorld.Controller.CLien;

import com.tscode.LitWorld.Database.CategoryClass.CategoryClass;
import com.tscode.LitWorld.Database.CategoryClass.CategoryRepository;
import com.tscode.LitWorld.Database.StoryClass.QuerryStory;
import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.StoryRepository;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Database.UserClass.UserRepository;
import com.tscode.LitWorld.Service.PageService;
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
import java.util.Arrays;
import java.util.List;


@Controller
public class HomeClienController {

    @Autowired
    SessionService session;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    StoryRepository storyRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PageService pageService;
    @Autowired
    QuerryStory querryStory;


    @RequestMapping("/home")
    public String home(Model model, HttpServletRequest request) {
        List<CategoryClass> listStory = session.get("liststory");
        int lengthList = (listStory != null) ? listStory.size() : 0;
        System.out.println("Độ dài của danh sách: " + lengthList);
        int totalPages = pageService.calculateTotalPages(lengthList, 6);
        System.out.println("Tổng số trang là: " + totalPages);


        model.addAttribute("currentPage", 0);
        model.addAttribute("totalPages", totalPages);
//        model.addAttribute("stories", stories);






        return "component/ClienComponets/list";
    }


    @RequestMapping("/home/test")
    public String test(Model model, HttpServletRequest request) {
        session.remove("list");
        return "redirect:/home";
    }



    @RequestMapping("/home-page/{id}")
    public String homePage(@PathVariable int id, Model model, HttpServletRequest request) {
        int idcurren = id - 1;
        List<CategoryClass> listStory = session.get("liststory");
        int lengthList = (listStory != null) ? listStory.size() : 0;
        System.out.println("Độ dài của danh sách: " + lengthList);
        int totalPages = pageService.calculateTotalPages(lengthList, 6);
        System.out.println("Tổng số trang là: " + totalPages);
        List<StoryClass> stories = querryStory.getStories(idcurren, 6);
        if (id > totalPages) {
            return "Error";
        } else {
            model.addAttribute("currentPage", idcurren);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("liststorycurren", stories);
            System.err.println("này là gì"+stories);
            return "component/ClienComponets/list";
        }

    }


    @PostMapping("/home/gio-hang")
    public String giohanga(@RequestBody List<String> userCart, Model model) {
        System.out.println("nó đây nè" + userCart);

        List<StoryClass> stories = new ArrayList<>(); // Tạo một danh sách mới để lưu các truyện
        int totalPrice = 0;
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
        System.out.println(stories);
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
        System.out.println("bị lỗi lol gif đaay" + user);
        userRepository.save(user);
        session.set("user", user);
        return "component/ClienComponets/User";
    }

    @RequestMapping("/{storyName}")
    public String getStory(@PathVariable("storyName") String storyName, Model model) {
        model.addAttribute("story", storyName);
        return "component/ClienComponets/list";
    }

    @ModelAttribute
    public void addAttributes(Model model, HttpServletRequest request) {
        session.set("liststory", storyRepository.findAll());
    }


    @RequestMapping("/the-loai/{name}")
    public String getTheLoai(@PathVariable("name") String name, Model model) {

        List<StoryClass> storyNames = new ArrayList<>();

        CategoryClass id  = categoryRepository.findByName(name);
        String idcategory = String.valueOf(id.getId());
        System.out.println("chưa hiểu"+idcategory);
        List<StoryClass> stories = session.get("liststory");

        if (stories != null) {
            for (StoryClass story : stories) {
                if (story.getCategory() != null) {
                    System.err.println("category của từng truyện: "+story.getCategory());
                    String category = story.getCategory().replace("[","").replace("]","");
                    System.out.println("mảng đã bỏ dấu"+category);
                    List<String> categories = Arrays.asList(category.split(","));
                    if(categories.stream().anyMatch(str -> str.trim().equals(idcategory))) {
                        storyNames.add(story);
                    }
                }
                else {
                    System.err.println("lần 1");
                }
            }
        }
        model.addAttribute("storyNames", storyNames);
        System.err.println(storyNames);
        return "component/ClienComponets/Category";
    }

    @ModelAttribute
    public void addAttributes(Model model) {
        List<CategoryClass> list = categoryRepository.findAll();
        model.addAttribute("Categorylist", list);
    }

//    public String createUrl(String name) {
//        return name.replace(" ", "-");
//    }
}
