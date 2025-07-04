package TY_PROJECT.Programs.service;

import TY_PROJECT.Programs.repository.AdminRepository;
import TY_PROJECT.ProgramsController.entity.Admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Autowired
    public AdminDetailsService(AdminRepository adminRepository) { 
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> {
                    System.out.println("❌ Admin not found: " + username);
                    return new UsernameNotFoundException("Admin not found");
                });

        System.out.println("Admin found: " + admin.getUsername());

        return User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword()) 
                .roles("ADMIN")
                .build();
    }

}
