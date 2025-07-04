package TY_PROJECT.Programs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;



import TY_PROJECT.Programs.repository.ContactRepository;
import TY_PROJECT.ProgramsController.entity.Contact;



@Controller
public class ContactusController {
    @Autowired
    private ContactRepository contactRepository;

    @GetMapping("/contactus")
    public String contactUs(Model model) {
        model.addAttribute("contact", new Contact()); 
        return "contactus";
    }

    @PostMapping("/submitContactForm")
    public String submitContactForm(@ModelAttribute Contact contact, Model model) {
        try {
        	contact.setStatus("Pending");
        	contactRepository.save(contact);

           
            model.addAttribute("successMessage", "Your message has been sent successfully!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Error sending message. Please try again.");
        }
        model.addAttribute("contact", new Contact()); 
        return "contactus";
    }
    @GetMapping("/user/my-queries")
    public String userQueries(@RequestParam String email, Model model) {
        List<Contact> queries = contactRepository.findByEmail(email);
        model.addAttribute("queries", queries);
        return "my-queries";
    }
}
