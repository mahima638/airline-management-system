package TY_PROJECT.Programs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import TY_PROJECT.Programs.service.FlightService;
import TY_PROJECT.ProgramsController.entity.Flight;

@Controller
@RequestMapping("/admin")
public class AdminFlightController {

    private final FlightService flightService;

    @Autowired
    public AdminFlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/manage/flight")
    public String showFlightManagement(@RequestParam(value = "mode", required = false, defaultValue = "view") String mode,
                                       Model model, 
                                       @RequestParam(value = "message", required = false) String message) {
    	List<Flight> flights = flightService.getAllFlightsSortedByNewest();

        model.addAttribute("flights", flights);
        model.addAttribute("mode", mode);
        model.addAttribute("flight", new Flight());
        model.addAttribute("message", message);
        return "admin-manage-flight";
    }

    @PostMapping("/manage/flight/add")
    public String addFlight(@ModelAttribute Flight flight, RedirectAttributes redirectAttributes) {
        flightService.addFlight(flight);
        redirectAttributes.addAttribute("message", "Flight Added Successfully!");
        return "redirect:/admin/manage/flight";
    }

    @PostMapping("/manage/flight/delete")
    public String deleteFlight(@RequestParam("flightId") Long flightId, RedirectAttributes redirectAttributes) {
        flightService.deleteFlight(flightId);
        redirectAttributes.addAttribute("message", "Flight Deleted Successfully!");
        return "redirect:/admin/manage/flight";
    }
}
