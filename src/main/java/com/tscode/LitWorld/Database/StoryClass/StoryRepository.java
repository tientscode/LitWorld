package com.tscode.LitWorld.Database.StoryClass;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoryRepository extends JpaRepository<StoryClass, Long> {
    StoryClass save(StoryClass story);
    boolean existsByname(String name);
   StoryClass deleteById(long id);
   StoryClass findById(long id);

    void save(Integer id);

    StoryClass getStoryByName(String replace);

    Page<StoryClass> findAll(Pageable pageable);

    @Query("SELECT s FROM StoryClass s ORDER BY s.quantity DESC")
    List<StoryClass> findTop12ByQuantityDesc(Pageable pageable);

}
