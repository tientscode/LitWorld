package com.tscode.LitWorld.Database.StoryClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<StoryClass, Long> {
    StoryClass save(StoryClass story);
    boolean existsByname(String name);
   StoryClass deleteById(long id);
   StoryClass findById(long id);

    void save(Integer id);

    StoryClass getStoryByName(String replace);
}
