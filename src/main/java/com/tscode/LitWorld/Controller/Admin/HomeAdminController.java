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
        model.addAttribute("liststory", list);
        return "component/AdminComponets/home";
    }


    @GetMapping("/user")
    public String allUser(Model model, HttpServletRequest request) {
        List<UserClass> users = querryUser.getClassUsers();
        model.addAttribute("users", users);
        System.out.println(users.get(0).getName());
        return "component/AdminComponets/User";
    }


    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showStoryForm(Model model, HttpServletRequest request) {
        StoryClass storyClass = new StoryClass();
        model.addAttribute("storyClass", storyClass);
        return "component/AdminComponets/CreateStory";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitStoryForm(@ModelAttribute("storyClass") StoryClass storyClass, HttpServletRequest request) {
        storyRepository.save(storyClass);
        return "redirect:/dashboard";
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