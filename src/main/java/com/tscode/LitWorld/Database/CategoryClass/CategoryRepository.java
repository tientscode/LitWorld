package com.tscode.LitWorld.Database.CategoryClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryClass, Long> {
    CategoryClass findByName(String name);
}
