package TY_PROJECT.Programs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import TY_PROJECT.Programs.repository.FlightRepository;
import TY_PROJECT.Programs.repository.BookingRepository;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/dashboard")
    public String showDashboard(Model model) {
        long totalFlights = flightRepository.count();
        long totalBookings = bookingRepository.count();

        model.addAttribute("totalFlights", totalFlights);
        model.addAttribute("totalBookings", totalBookings);

        return "admin-dashboard"; // Make sure your HTML file is admin-dashboard.html under templates
    }
}
