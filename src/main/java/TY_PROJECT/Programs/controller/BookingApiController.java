package TY_PROJECT.Programs.controller;

import java.time.LocalDate;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import TY_PROJECT.Programs.service.BookingService;
import TY_PROJECT.ProgramsController.entity.Booking;

@RestController
@RequestMapping("/api/bookings")
public class BookingApiController {

    @Autowired
    private BookingService bookingService;

    // 1. Create a booking
    @PostMapping
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody Booking booking) {
        booking.setBookingDate(LocalDate.now());
        Booking saved = bookingService.saveBooking(booking);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // 2. Get booking by ID
    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBookingById(@PathVariable Long id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok(booking);
    }

    // 3. Get all bookings
    @GetMapping
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    // 4. Cancel/Delete booking
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
}