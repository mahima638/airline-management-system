package TY_PROJECT.Programs.service;

import TY_PROJECT.Programs.repository.AdminRepository;
import TY_PROJECT.ProgramsController.entity.Admin;
import jakarta.annotation.PostConstruct;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminInitService implements CommandLineRunner {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminInitService(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (adminRepository.findByUsername("admin").isEmpty()) {
            Admin admin = new Admin();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));  
            adminRepository.save(admin);
            Admin mahima = new Admin();

            admin.setUsername("mahima");
            admin.setPassword(passwordEncoder.encode("mahima123"));  
            adminRepository.save(mahima);
            System.out.println("Admin user created: admin/admin123");
        } else {
            System.out.println("Admin user already exists. Skipping creation.");
        }
        
    }
    @PostConstruct
    public void generatePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "admin123";
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Hashed Password: " + encodedPassword);
    }
}
