package TY_PROJECT.Programs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import TY_PROJECT.ProgramsController.entity.Admin;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
