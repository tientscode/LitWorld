package com.tscode.LitWorld.Database.RoleClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleClass, Long> {
    RoleClass findByName(String name);
}
