package com.tscode.LitWorld.implement;

import com.tscode.LitWorld.Database.UserClass.QuerryUser;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Service
public class AdminInterceptor implements HandlerInterceptor {

    @Autowired
    private QuerryUser querryUser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        List<UserClass> users = querryUser.getClassUsers();
        System.out.println("lỗi ở đây nè 27" + users);
        ModelAndView mv = new ModelAndView();
        mv.addObject("users", users);
        return true;
    }
}

