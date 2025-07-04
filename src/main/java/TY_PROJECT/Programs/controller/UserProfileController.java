package TY_PROJECT.Programs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import TY_PROJECT.ProgramsController.entity.Booking;



@Controller
public class UserProfileController {

  
    @GetMapping("/user-profile")
    public String showUserProfileForm(Model model) {
        model.addAttribute("booking", new Booking());
        return "userprofile"; 
    }

   
    @PostMapping("/user-profile")
    public String userProfile(@ModelAttribute Booking booking, Model model) {
        model.addAttribute("booking", booking);
        return "userprofile";
    }
}
