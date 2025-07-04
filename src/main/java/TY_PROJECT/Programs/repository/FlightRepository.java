package TY_PROJECT.Programs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import TY_PROJECT.ProgramsController.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findBySourceAndDestinationAndDepartureDate(String source,String destination, LocalDate departureDate);
	 List<Flight> findAllByOrderByCreatedAtDesc();

	

}
