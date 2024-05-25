package com.tscode.LitWorld.Database.UserClass;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserClass, Integer> {
    UserClass findByAccount(String account);


    boolean existsByaccount(String account);


}
