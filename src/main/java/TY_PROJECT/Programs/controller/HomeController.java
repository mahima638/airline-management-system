package TY_PROJECT.Programs.controller;

import java.util.List;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import TY_PROJECT.Programs.dto.Destination;

@Controller
public class HomeController {

    private List<Destination> destinations = List.of(
            new Destination("Cyprus", "El Noda", "/Assets/cyprus.jpg", "345"),
            new Destination("Paris", "France", "/Assets/france.jpg", "342"),
            new Destination("Estonia", "Estonia", "/Assets/estonia.webp", "453"),
            new Destination("Germany", "Germany", "/Assets/germany.jpg", "453"),
            new Destination("Greece", "Greece", "/Assets/greece.jpg", "453"),
            new Destination("India", "Taj Mahal", "/Assets/india.jpeg", "453")
    );
    private int currentIndex = 0;

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("destinations", destinations);
        model.addAttribute("currentIndex", currentIndex);
        return "home";
    }

    @PostMapping("/home/next")
    public String nextSlide(Model model) {
        if (!destinations.isEmpty()) {
            currentIndex = (currentIndex + 1) % destinations.size();
        }
        model.addAttribute("destinations", destinations);
        model.addAttribute("currentIndex", currentIndex);
        return "home";
    }

    @PostMapping("/home/previous")
    public String previousSlide(Model model) {
        if (!destinations.isEmpty()) {
            currentIndex = (currentIndex - 1 + destinations.size()) % destinations.size(); // Decrement index by 1, wrap around if necessary
        }
        model.addAttribute("destinations", destinations);
        model.addAttribute("currentIndex", currentIndex);
        return "home";
    }


}
