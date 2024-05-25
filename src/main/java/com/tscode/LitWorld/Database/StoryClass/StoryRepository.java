package com.tscode.LitWorld.Database.StoryClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<StoryClass, Long> {

    boolean existsByname(String name);
}
