package com.tscode.LitWorld.Database.StoryClass;

import com.tscode.LitWorld.Dto.StoryDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuerryStory implements khaibaohamStory {

    @Autowired
    private StoryRepository storyRepository;

//    @Override
//    public StoryClass save(StoryDto storyDto) {
//        if (storyRepository.existsByname(storyDto.getName())) {
//            throw new RuntimeException("truyện đã tồn tại");
//        }
//        StoryClass storyClass = new StoryClass();
//        storyClass.setName(storyDto.getName());
//        storyClass.setDescription(storyDto.getDescription());
//        storyClass.setImage(storyDto.getImage());
//        return storyRepository.save(storyClass);
//    }


    @Override
    public List<StoryClass> getlistStory() {
        return storyRepository.findAll();
    }


}
