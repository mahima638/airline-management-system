package TY_PROJECT.Programs.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import TY_PROJECT.Programs.service.BookingService;
import TY_PROJECT.ProgramsController.entity.Booking;

import java.util.List;

@Controller
@RequestMapping("/admin/manage/booking")
public class AdminBookingController {

    @Autowired
    private BookingService bookingService;

    @GetMapping
    public String manageBookings(@RequestParam(value = "mode", required = false, defaultValue = "view") String mode, Model model) {
        model.addAttribute("mode", mode);
        if (mode.equals("view") || mode.equals("delete")) {
            List<Booking> bookings = bookingService.getAllBookings();
            model.addAttribute("bookings", bookings);
        } else if (mode.equals("add")) {
            model.addAttribute("booking", new Booking());
        }
        return "admin-manage-booking";
    }

    @PostMapping("/add")
    public String addBooking(@ModelAttribute Booking booking, Model model) {
        bookingService.saveBooking(booking);
        model.addAttribute("message", "Booking added successfully!");
        return "redirect:/admin/manage/booking?mode=view";
    }

    @PostMapping("/delete")
    public String deleteBooking(@RequestParam("bookingId") Long bookingId, Model model) {
        bookingService.deleteBooking(bookingId);
        model.addAttribute("message", "Booking deleted successfully!");
        return "redirect:/admin/manage/booking?mode=delete";
    }
}
