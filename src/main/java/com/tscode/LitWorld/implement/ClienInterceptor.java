package com.tscode.LitWorld.implement;

import com.tscode.LitWorld.Database.StoryClass.StoryClass;
import com.tscode.LitWorld.Database.StoryClass.StoryRepository;
import com.tscode.LitWorld.Service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Service
public class ClienInterceptor implements HandlerInterceptor {
    @Autowired
    SessionService sessionService;
    @Autowired
    private StoryRepository storyRepository;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            List<StoryClass> topStories = storyRepository.findTop12ByQuantityDesc(Pageable.ofSize(12));
            System.out.println("chuỗi 12 "+ topStories.size());
            sessionService.set("topStories", topStories);
        }
    }

}
