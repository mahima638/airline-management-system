package TY_PROJECT.Programs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminLogoutController {

    @GetMapping("/logoutPage")
    public String showLogoutPage() {
        return "logout"; 
    }
}
