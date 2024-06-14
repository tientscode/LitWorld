package com.tscode.LitWorld.implement;

import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;


import java.util.List;


@Service
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private QuerryUser querryUser;
    @Autowired
    SessionService session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<UserClass> user = querryUser.getClassUsers();
//        System.out.println("lỗi ở đây nè 27" + user);
        session.set("listuser", user);
        return true;
    }
}

