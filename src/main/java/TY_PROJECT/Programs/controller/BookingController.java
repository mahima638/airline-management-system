package TY_PROJECT.Programs.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import TY_PROJECT.Programs.repository.BookingRepository;
import TY_PROJECT.Programs.service.BookingService;
import TY_PROJECT.Programs.service.FlightService;
import TY_PROJECT.ProgramsController.entity.Booking;
import TY_PROJECT.ProgramsController.entity.Flight;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    private BookingService bookingService;

    @Autowired
    private FlightService flightService;
    
    @Autowired 
    private BookingRepository bookingrepository;

    @GetMapping
    public String bookFlight(HttpSession session, Model model) {
        Long flightId = (Long) session.getAttribute("selectedFlightId");
        String departureCities = (String) session.getAttribute("departure_cities");
        String arrivalCities = (String) session.getAttribute("arrival_cities");
        String tripType = (String) session.getAttribute("tripType");

        if (tripType == null || tripType.isEmpty()) {
            tripType = "oneway";
            session.setAttribute("tripType", tripType);
        }

        model.addAttribute("flightNumber", flightId);
        model.addAttribute("departure_cities", departureCities);
        model.addAttribute("arrival_cities", arrivalCities);
        model.addAttribute("tripType", tripType);
        model.addAttribute("booking", new Booking());

        return "booking";
    }

    @GetMapping("/book")
    public String showBookingForm(Model model, HttpSession session) {
        String tripType = (String) session.getAttribute("tripType");
        if (tripType == null || tripType.isEmpty()) {
            tripType = "oneway";
            session.setAttribute("tripType", tripType);
        }
        model.addAttribute("booking", new Booking());
        model.addAttribute("tripType", tripType);
        model.addAttribute("email", "");
        model.addAttribute("password", "");
        model.addAttribute("passportNumber", "");

        return "booking";
    }

    @PostMapping("/book")
    public String processBooking(
            @Valid @ModelAttribute Booking booking,
            BindingResult bindingResult,
            HttpSession session,
            RedirectAttributes redirectAttributes,
            @RequestParam(value = "tripType", required = false) String tripType,
            Model model) {
    	boolean emailExists = bookingrepository.existsByEmail(booking.getEmail());
    	
    	  if (emailExists) {
    	        model.addAttribute("emailError", "Same gmail not allowed.");
    	        return "booking"; 
    	    }
    	 


        if (tripType == null || tripType.isEmpty()) {
            tripType = (String) session.getAttribute("tripType");
        }
        if (tripType == null || tripType.isEmpty()) {
            tripType = "oneway"; 
        }

        booking.setTripType(tripType);
        session.setAttribute("tripType", tripType);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errorMessage", "Invalid data! Please check your inputs.");
            return "booking";
        }
        System.out.println("Passport Number: " + booking.getPassportNumber());
  
        booking.setDeparture_cities((String) session.getAttribute("departure_cities"));
        booking.setArrival_cities((String) session.getAttribute("arrival_cities"));

        Booking savedBooking = bookingService.saveBooking(booking);
        session.setAttribute("userDetails", savedBooking);

       
        session.setAttribute("email", booking.getEmail());
        session.setAttribute("passportNumber", booking.getPassportNumber());

      
        redirectAttributes.addFlashAttribute("bookingSuccess", true);
        redirectAttributes.addFlashAttribute("bookingId", savedBooking.getId());
        redirectAttributes.addFlashAttribute("flightId", savedBooking.getFlightid());
        redirectAttributes.addFlashAttribute("tripType", savedBooking.getTripType());
        redirectAttributes.addFlashAttribute("email", savedBooking.getEmail());
        redirectAttributes.addFlashAttribute("passportNumber", savedBooking.getPassportNumber());

        return "redirect:/payment?bookingId=" + savedBooking.getId() + "&tripType=" + savedBooking.getTripType();
    }

    @GetMapping("/userDetails")
    @ResponseBody
    public Booking getUserDetails(HttpSession session) {
        Booking userBooking = (Booking) session.getAttribute("userDetails");

        if (userBooking != null) {
            userBooking.setEmail((String) session.getAttribute("email"));
            userBooking.setPassportNumber((String) session.getAttribute("passportNumber"));
        }
        return userBooking;
    }


    @GetMapping("/{flightId}")
    public String showBookingPage(@PathVariable Long flightId, HttpSession session, Model model) {
        Flight flight = flightService.getFlightById(flightId);
        String tripType = (String) session.getAttribute("tripType");
        if (tripType == null || tripType.isEmpty()) {
            tripType = "oneway"; 
            session.setAttribute("tripType", tripType);
        }

        model.addAttribute("flight", flight);
        model.addAttribute("flightId", flightId);
        model.addAttribute("departure_cities", flight.getSource());
        model.addAttribute("arrival_cities", flight.getDestination());
        model.addAttribute("tripType", tripType);

        session.setAttribute("departure_cities", flight.getSource());
        session.setAttribute("arrival_cities", flight.getDestination());
        session.setAttribute("tripType", tripType);

        return "booking";
    }

   

    @PostMapping("/search")
    public String getBookingDetails(@RequestParam("identifier") String identifier, Model model) {
        Optional<Booking> booking;

        try {
            Long bookingId = Long.parseLong(identifier);
            booking = Optional.ofNullable(bookingService.getBookingById(bookingId)); 
        } catch (NumberFormatException e) {
            booking = bookingService.getBookingByIdOrNameOrEmailOrPhone(identifier); 
        }

        if (booking.isPresent()) {
            model.addAttribute("booking", booking.get());
            model.addAttribute("email", booking.get().getEmail());
            model.addAttribute("passportNumber", booking.get().getPassportNumber());
        } else {
            model.addAttribute("error", "No booking found.");
        }

        return "userprofile";
    }


  
}

