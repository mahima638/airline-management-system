package TY_PROJECT.Programs.service;

import TY_PROJECT.Programs.repository.AdminRepository;
import TY_PROJECT.Programs.repository.UserRepository;
import TY_PROJECT.ProgramsController.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        // First check users table by email
        return userRepository.findByEmail(username)
                .map(user -> org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .roles("USER")
                        .build())
                // If not found in users, check admin table
                .orElseGet(() -> adminRepository.findByUsername(username)
                        .map(admin -> org.springframework.security.core.userdetails.User.builder()
                                .username(admin.getUsername())
                                .password(admin.getPassword())
                                .roles("ADMIN")
                                .build())
                        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username)));
    }
}