package com.tscode.LitWorld.Controller.Admin;

import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.StoryRepository;
import com.tscode.LitWorld.Database.StoryClass.khaibaohamStory;
import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Database.UserClass.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/dashboard")
public class HomeAdminController {

    @Autowired
    StoryRepository storyRepository;

    @Autowired
    private khaibaohamStory khaibaohamStory;

    @Autowired
    private QuerryUser querryUser;

    @Autowired
    UserRepository userRepository;

    @GetMapping()
    public String dashboard(Model model, HttpServletRequest request) {
        List<StoryClass> list = khaibaohamStory.getlistStory();
        model.addAttribute("liststory", list);
        return "component/AdminComponets/Home";
    }


    @GetMapping("/user")
    public String allUser(Model model, HttpServletRequest request) {
        List<UserClass> users = querryUser.getClassUsers();

//        model.addAttribute("users", users);
//        System.out.println(users.get(0).getName());
        return "component/AdminComponets/User";
    }




    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String showStoryForm(Model model, HttpServletRequest request) {
        StoryClass storyClass = new StoryClass();
        model.addAttribute("storyClass", storyClass);
        return "component/AdminComponets/CreateStory";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitStoryForm(@RequestParam("categories[]") List<String> categories, @ModelAttribute("storyClass") StoryClass storyClass, HttpServletRequest request) {
        storyClass.setCategory(String.valueOf(categories));
        storyRepository.save(storyClass);
        return "redirect:/dashboard";
    }

    @RequestMapping("/edit/delete/{id}")
    public String deleteidstory(@PathVariable("id") Long id) {
        storyRepository.deleteById(id);
        return "redirect:/dashboard";
    }


    //caajp nhat
    @RequestMapping("/edit/story/{id}")
    public String editstory(@PathVariable("id") Long id, Model model) {
        StoryClass storyClass = storyRepository.findById(id).get();
        model.addAttribute("storyClass",storyClass);
        System.err.println(storyClass );
        return "component/AdminComponets/CreateStory";
    }



    @RequestMapping("/user/delete/{id}")
    public String deleteiduser(@PathVariable("id") Integer id) {
        System.out.println("id cần xóa là" + id);
        userRepository.deleteById(id);
        return "redirect:/dashboard/user";
    }


    @RequestMapping("/story/edit/{id}")
    public String editstory(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("storyid", storyRepository.findById(id));
        storyRepository.save(id);
        return "redirect:/dashboard/create";
    }

    @RequestMapping("/user/{id}")
    public String edit(Model model, @PathVariable("id") Integer id) {
        UserClass usercurren = userRepository.findAllById(id);
        model.addAttribute("usercurren", usercurren);
//        List<Categories> items = categoryDAO.findAll();
//        model.addAttribute("items", items);
        return "component/AdminComponets/User";
    }


    @RequestMapping("/user/update")
    public String update(UserClass item) {
        userRepository.save(item);
        return "redirect:/dashboard/user/" + item.getId();
    }


}