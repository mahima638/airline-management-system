package TY_PROJECT.Programs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import TY_PROJECT.Programs.repository.PaymentRepository;
import TY_PROJECT.Programs.service.BookingService;
import TY_PROJECT.Programs.service.PaymentService;
import TY_PROJECT.ProgramsController.entity.Booking;
import TY_PROJECT.ProgramsController.entity.Payment;
import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping("/payment")
    public String showPaymentPage(
        @RequestParam(value = "bookingId", required = false) Long bookingId,
        @RequestParam(value = "paymentMethod", required = false) String paymentMethod,
        @RequestParam(value = "ticketPrice", required = false) Double ticketPrice, 
        @RequestParam(value = "tripType", required = false) String tripType,  
        HttpSession session,  
        Model model
    ) {
    	
        if (bookingId == null) {
            model.addAttribute("errorMessage", "Please select a flight before proceeding to payment.");
            return "payment";
        }

        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            model.addAttribute("errorMessage", "Booking not found. Please try again.");
            return "payment"; 
        }

        if (tripType != null && !tripType.isEmpty()) {
            session.setAttribute("tripType", tripType);  
        }

        String departure_cities = booking.getDeparture_cities();
        String arrival_cities = booking.getArrival_cities();
        String special_request = booking.getSpecial_request();
        String email = booking.getEmail();

        if (ticketPrice == null) {
            ticketPrice = bookingService.calculateTicketPrice(booking);
        }

        model.addAttribute("booking", booking);
        model.addAttribute("bookingId", bookingId);
        model.addAttribute("ticketPrice", ticketPrice);
        model.addAttribute("departure_cities", departure_cities);
        model.addAttribute("arrival_cities", arrival_cities);
        model.addAttribute("special_request", special_request);
        model.addAttribute("bookingSuccess", true);
        model.addAttribute("paymentSuccess", true);
        model.addAttribute("tripType", tripType); 
        model.addAttribute("email", email);

        Payment payment = new Payment();
        if (paymentMethod != null) {
            payment.setPaymentMethod(paymentMethod);
        }
        model.addAttribute("payment", payment);

        return "payment";  
    }

    @PostMapping("/processPayment")
    public String processPayment(
            @RequestParam(value = "bookingId", required = false) Long bookingId,
            @RequestParam(value = "ticketPrice", required = false) Double ticketPrice,
            @RequestParam(value = "tripType", required = false) String tripType,  // <-- Add tripType
            @ModelAttribute Payment payment, Model model,
            HttpSession session,
            RedirectAttributes redirectAttributes) {
    	
    	

        if (bookingId == null || bookingId <= 0) {
            model.addAttribute("errorMessage", "Invalid booking ID.");
            return "payment";
        }
        if (tripType == null || tripType.isEmpty()) {
            tripType = (String) session.getAttribute("tripType");
        }


        try {
            if (!isValidPayment(payment)) {
                model.addAttribute("errorMessage", "Please enter all required payment details.");
                model.addAttribute("payment", payment);
                model.addAttribute("bookingId", bookingId);
                model.addAttribute("ticketPrice", ticketPrice);  
                return "payment";
            }

            payment.setBookingId(bookingId);
            paymentService.processPayment(payment);
            paymentRepository.save(payment);

            session.setAttribute("tripType", tripType);  

            redirectAttributes.addFlashAttribute("bookingId", bookingId);
            redirectAttributes.addFlashAttribute("ticketPrice", ticketPrice);

            return "redirect:/paymentSuccess?bookingId=" + bookingId + "&tripType=" + tripType; 

        } catch (Exception e) {
            model.addAttribute("errorMessage", "There was an error processing the payment.");
            model.addAttribute("ticketPrice", ticketPrice); 
            return "payment";
        }
    }

    private boolean isValidPayment(Payment payment) {
        switch (payment.getPaymentMethod()) {
            case "credit_card":
            case "debit_card":
                return payment.getCardNumber() != null && !payment.getCardNumber().isEmpty() &&
                       payment.getExpiryDate() != null && !payment.getExpiryDate().isEmpty() &&
                       payment.getCvv() != null && !payment.getCvv().isEmpty();
            case "upi":
                return payment.getUpiId() != null && !payment.getUpiId().isEmpty();
            case "net_banking":
                return payment.getBank() != null && !payment.getBank().isEmpty() &&
                       payment.getAccountHolderName() != null && !payment.getAccountHolderName().isEmpty() &&
                       payment.getAccountNumber() != null && !payment.getAccountNumber().isEmpty() &&
                       payment.getIfscCode() != null && !payment.getIfscCode().isEmpty() &&
                       payment.getTransactionPassword() != null && !payment.getTransactionPassword().isEmpty();
            default:
                return false;
        }
    }


    @GetMapping("/paymentSuccess")
    public String showPaymentSuccessPage(HttpSession session, Model model) {
        Long bookingId = (Long) model.getAttribute("bookingId");
        Double ticketPrice = (Double) model.getAttribute("ticketPrice");
        String tripType = (String) session.getAttribute("tripType");
        String email = (String) session.getAttribute("email"); 

        if (bookingId == null) {
            model.addAttribute("errorMessage", "Booking ID is missing!");
            return "paymentSuccess"; 
        }

        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            model.addAttribute("errorMessage", "Booking not found!");
            return "paymentSuccess";
        }

        model.addAttribute("bookingId", booking.getId()); 
        model.addAttribute("name", booking.getName());  
        model.addAttribute("phoneno", booking.getPhoneno());
        model.addAttribute("arrival_cities", booking.getArrival_cities());
        model.addAttribute("departure_cities", booking.getDeparture_cities());
        model.addAttribute("nationality", booking.getNationality());
        model.addAttribute("dob", booking.getDob()); 
        model.addAttribute("ticketPrice", ticketPrice);
        model.addAttribute("tripType", tripType); 
        model.addAttribute("email", email); 
        return "paymentSuccess"; 
    }
}