package com.tscode.LitWorld.Controller.Admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeAdminController {
    @GetMapping("/dashboard")
    public String dashboard() {
        return "homeadmin";
    }
}
