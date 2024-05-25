package com.tscode.LitWorld.Controller.Admin;

import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.khaibaohamStory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeAdminController {


    @Autowired
    private khaibaohamStory khaibaohamStory;


    @RequestMapping("/dashboard")
    public String dashboard(Model model) {
        List<StoryClass> list = khaibaohamStory.getlistStory();
//        System.out.println(list);
        model.addAttribute("liststory", list);
        return "homeadmin";
    }


}
