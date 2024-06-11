package com.tscode.LitWorld.implement;

import com.tscode.LitWorld.Database.RoleClass.RoleClass;
import com.tscode.LitWorld.Database.UserClass.UserClass;
import com.tscode.LitWorld.Service.SessionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    SessionService session;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        UserClass user = session.get("user");
        String error = "";
        if (user == null) {
            System.out.println("lỗiở đây 26 authinterceptor");
            error = "Please login!";
            response.sendRedirect("/login");
            return false;

        } else {
            Set<String> roles = user.getRoles().stream()
                    .map(RoleClass::getName)
                    .collect(Collectors.toSet());
            System.out.println("User roles saved in session: " + ((UserClass)session.get("user")).getRoles());
//            session.set("isAdmin", roles.contains("Role_Admin"));
            if (roles.contains("Role_User") && uri.startsWith("/dashboard")) {
                System.out.println("lỗi ở authinterceptor 38");
                error = "Access denied!";
                session.set("security-uri", uri);
                response.sendRedirect("/login?error=" + error);
                return false;
            }
        }
        return true;
    }



}