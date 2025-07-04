package TY_PROJECT.Programs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import TY_PROJECT.ProgramsController.entity.User;

@Controller
public class UserDetails {
	
	@GetMapping("/register")
	public  String ShowRegisteration(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	@GetMapping("user/home")
	public  String ShowHome(Model model) {
	
		return "home";
	}
	@GetMapping("user/booking")
	public  String ShowBooking(Model model) {
	
		return "booking";
	}
	@GetMapping("user/contactus")
	public  String ShowContactUs(Model model) {
	
		return "contactus";
	}
	@GetMapping("user/flightsearch")
	public  String ShowFlightSearch(Model model) {
	
		return "flightsearch";
	}
	@GetMapping("user/userprofile")
	public  String ShowUserProfile(Model model) {
	
		return "userprofile";
	}
	@GetMapping("user/payment")
	public  String ShowPayment(Model model) {
	
		return "payment";
	}

}
