package com.tscode.LitWorld.Controller.Admin;

import com.tscode.LitWorld.Database.RoleClass.RoleClass;
import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.StoryRepository;
import com.tscode.LitWorld.Database.StoryClass.khaibaohamStory;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Dto.StoryDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class HomeAdminController {

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    private khaibaohamStory khaibaohamStory;

    @Autowired
    private QuerryUser querryUser;

    @GetMapping()
    public String dashboard(Model model, HttpServletRequest request) {
        List<StoryClass> list = khaibaohamStory.getlistStory();
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
                                return "error";
                            }
                        } catch (Exception e) {
                            return "error";
                        }
                    }
                }
            }
        }
        return "auth/login";
    }



    @GetMapping("/user")
    public String allUser(Model model, HttpServletRequest request) {
        List<UserClass> users = querryUser.getClassUsers();
        System.out.println(users.get(0).getName());
        model.addAttribute("users", users);
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    UserClass user = querryUser.findByAccount(username);
                    model.addAttribute("user", user);
                    model.addAttribute("users", users);
                    return "component/AdminComponets/User";
                }
            }
        }
        return "redirect:/login";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showStoryForm(Model model, HttpServletRequest request) {
        StoryClass storyClass = new StoryClass();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    String username = cookie.getValue();
                    UserClass user = querryUser.findByAccount(username);
                    model.addAttribute("user", user);
                    model.addAttribute("storyClass", storyClass);
                    return "component/AdminComponets/CreateStory";
                }
            }
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitStoryForm(@ModelAttribute("storyClass") StoryClass storyClass, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    storyRepository.save(storyClass);
                    return "redirect:/dashboard";
                }
            }
        }
        return "redirect:/login";
    }

    @RequestMapping("/edit/delete/{id}")
    public String deleteidstory(@PathVariable("id") Long id) {
        storyRepository.deleteById(id);
        return "redirect:/dashboard";
    }

//    @RequestMapping("/edit/{id}")
//    public String edit(Model model, @PathVariable("id") String id) {
//        Categories item = categoryDAO.findById(id).get();
//        model.addAttribute("item", item);
//        List<Categories> items = categoryDAO.findAll();
//        model.addAttribute("items", items);
//        return "index";
//    }


}