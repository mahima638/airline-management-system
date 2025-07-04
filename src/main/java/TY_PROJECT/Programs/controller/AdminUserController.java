package TY_PROJECT.Programs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminUserController {
	@GetMapping("/manage/user")
    public String showAdminLoginPage() {
        return "admin-manage-user"; 
    }

}
