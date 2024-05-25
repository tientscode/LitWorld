package com.tscode.LitWorld.Database.StoryClass;

import com.tscode.LitWorld.Dto.StoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuerryStory implements khaibaohamStory {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public StoryClass addStory(StoryDto storyDto) {
        if (storyRepository.existsByname(storyDto.getNamestory())) {
            throw new RuntimeException("accoutn already exists");
        }
        StoryClass storyClass = new StoryClass();
        storyClass.setName(storyDto.getNamestory());
        storyClass.setDescription(storyDto.getDescription());
        storyClass.setImage(storyDto.getImage());
        return storyRepository.save(storyClass);
    }


    @Override
    public List<StoryClass> getlistStory() {
        return storyRepository.findAll();
    }
}
