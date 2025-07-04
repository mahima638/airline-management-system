package TY_PROJECT.Programs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import TY_PROJECT.ProgramsController.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long>{
	  List<Contact> findByEmail(String email); 

}
