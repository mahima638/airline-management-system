package TY_PROJECT.Programs.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import TY_PROJECT.ProgramsController.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	Booking findById(long id);
	  Optional<Booking> findByName(String name);
	  Optional<Booking> findByEmail(String email);
	  Optional<Booking> findByPassportNumber(String passportNumber);
	Optional<Booking> findByPhoneno(String identifier);
	long countByBookingDate(LocalDate date);
	boolean existsByEmail(String email);
	boolean existsByPhoneno(String phoneno);
	boolean existsByPassportNumber(String passportNumber);
	boolean existsByPassword(String password);
}

