package com.tscode.LitWorld.Database.UserClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserClass, Integer> {
    UserClass findByAccount(String account);
    UserClass findByEmail(String email);

    boolean existsByaccount(String account);



}
