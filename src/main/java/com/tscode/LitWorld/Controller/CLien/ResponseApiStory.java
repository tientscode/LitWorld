package com.tscode.LitWorld.Controller.CLien;

import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.StoryRepository;
import com.tscode.LitWorld.Database.StoryClass.khaibaohamStory;
import com.tscode.LitWorld.Dto.StoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/story")
public class ResponseApiStory {

    @Autowired
    private khaibaohamStory  khaibaohamStory;
    @Autowired
    private StoryRepository storyRepository;

    @PostMapping("/add")
    public StoryClass addStory(@RequestBody StoryClass storyClass) {
        return storyRepository.save(storyClass);
    }

    @GetMapping("/list")
    public List<StoryClass> getListStory() {
        return khaibaohamStory.getlistStory();
    }
}
