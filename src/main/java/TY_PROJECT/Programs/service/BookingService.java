package TY_PROJECT.Programs.service;
import TY_PROJECT.Programs.exception.BookingNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import TY_PROJECT.Programs.repository.BookingRepository;
import TY_PROJECT.ProgramsController.entity.Booking;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    
    public Booking getBookingById(Long bookingId) {
        return bookingRepository.findById(bookingId)
        		.orElseThrow(() -> new BookingNotFoundException(bookingId));    }

   
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    public long countBookings() {
        return bookingRepository.count();
    }

    public long countTodayBookings() {
        return bookingRepository.countByBookingDate(LocalDate.now());
    }

   
    public void deleteBooking(Long bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new RuntimeException("Booking not found with ID: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
    }

    public Optional<Booking> getBookingByIdOrNameOrEmailOrPhone(String identifier) {
        try {
            Long id = Long.parseLong(identifier);
            return bookingRepository.findById(id);
        } catch (NumberFormatException e) {
            Optional<Booking> bookingByName = bookingRepository.findByName(identifier);
            if (bookingByName.isPresent()) return bookingByName;

            Optional<Booking> bookingByEmail = bookingRepository.findByEmail(identifier);
            if (bookingByEmail.isPresent()) return bookingByEmail;

            return bookingRepository.findByPhoneno(identifier); 
        }
    }

  
    public double calculateTicketPrice(Booking booking) {
        String departureCity = booking.getDeparture_cities(); 
        String arrivalCity = booking.getArrival_cities();
        int noOfBags = booking.getNo_of_bags();
        String seatPrefered = booking.getSeat_prefered();
        String tripType = booking.getTripType();

        double basePrice = 1500; 

        if ("Delhi".equalsIgnoreCase(departureCity) && "Mumbai".equalsIgnoreCase(arrivalCity)) {
            basePrice = 1000;
        } else if ("Mumbai".equalsIgnoreCase(departureCity) && "Jodhpur".equalsIgnoreCase(arrivalCity)) {
            basePrice = 2000;
        }

        if (seatPrefered != null) {
            switch (seatPrefered.toLowerCase()) {
                case "business":
                    basePrice *= 1.5;
                    break;
                case "firstclass":
                    basePrice *= 2;
                    break;
                case "economy":
                    basePrice *= 3;
                    break;
                default:
                    break;
            }
        }

        if (booking.getSpecial_request() != null && !booking.getSpecial_request().isEmpty()) {
            basePrice += 100;
        }

        if ("return".equalsIgnoreCase(tripType)) {
            basePrice *= 2;
        }

        return basePrice * noOfBags;
    }
}
