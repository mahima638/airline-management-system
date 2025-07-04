package TY_PROJECT.Programs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import TY_PROJECT.Programs.repository.ContactRepository;
import TY_PROJECT.ProgramsController.entity.Contact;

@Controller
@RequestMapping("/admin")
public class AdminQueryController {

    @Autowired
    private ContactRepository contactRepository;

    
    @GetMapping("/manage/queries")
    public String viewQueries(Model model) {
        model.addAttribute("queries", contactRepository.findAll());
        return "admin-queries";
    }

    
    @PostMapping("/respond/{id}")
    public String respondToQuery(@PathVariable Long id, @RequestParam String response) {
        Contact query = contactRepository.findById(id).orElse(null);
        if (query != null) {
            query.setAdminResponse(response);
            query.setStatus("Resolved");  
            contactRepository.save(query);
        }
        return "redirect:/admin/manage/queries";
    }
}
