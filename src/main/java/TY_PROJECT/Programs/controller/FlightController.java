package TY_PROJECT.Programs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import TY_PROJECT.Programs.service.FlightService;
import TY_PROJECT.ProgramsController.entity.Flight;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/flights")
public class FlightController {
    
    @Autowired
    private FlightService flightService;


    @GetMapping("/flightsearch")
    public String flightSearch() {
        return "flightsearch"; 
    }
    @GetMapping("/selectFlight")
    public String selectFlight(@RequestParam("flightId") Long flightId, HttpSession session) {
        session.setAttribute("selectedFlightId", flightId);
        System.out.println("Stored in Session - Flight ID: " + flightId);
        return "redirect:/booking"; // Redirect to BookingController
    }

    

   
    @PostMapping("/search")
    public String searchFlights(
            @RequestParam("departure_cities") String departure_cities,
            @RequestParam("arrival_cities") String arrival_cities,
            @RequestParam("date") String date,
            @RequestParam("tripType") String tripType,
            Model model, HttpSession session) {

        List<Flight> availableFlights = flightService.findFlights(departure_cities, arrival_cities, date);
        model.addAttribute("flights", availableFlights); 
        session.setAttribute("departure_cities", departure_cities);
        session.setAttribute("arrival_cities",arrival_cities);
        session.setAttribute("tripType", tripType);
        
        return "flightsearch"; 
    }
}
