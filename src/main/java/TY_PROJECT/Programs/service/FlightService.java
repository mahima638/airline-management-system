package TY_PROJECT.Programs.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import TY_PROJECT.Programs.repository.FlightRepository;
import TY_PROJECT.ProgramsController.entity.Flight;
import jakarta.transaction.Transactional;

@Service
public class FlightService {
	@Autowired
	private FlightRepository flightRepository;
	public List<Flight> findFlights(String source,String destination ,String date){
		LocalDate departureDate = LocalDate.parse(date);
		List<Flight> flights =flightRepository.findBySourceAndDestinationAndDepartureDate(source, destination, departureDate);
		System.out.println(flights);
		return flights;
		}
	public List<Flight> getAllFlightsSortedByNewest() {
	    return flightRepository.findAllByOrderByCreatedAtDesc();
	}

	public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
    }
	public long countFlights() {
        return flightRepository.count();
    }

   
    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

  
    @Transactional
    public Flight addFlight(Flight flight) {
        return flightRepository.save(flight);
    }

   
    @Transactional
    public Flight updateFlight(Long id, Flight updatedFlight) {
        return flightRepository.findById(id).map(flight -> {
          
            flight.setSource(updatedFlight.getSource());
            flight.setDestination(updatedFlight.getDestination());
            flight.setDepartureDate(updatedFlight.getDepartureDate());
            flight.setArrival_time(updatedFlight.getArrival_time());
            
           
            return flightRepository.save(flight);
        }).orElseThrow(() -> new RuntimeException("Flight not found"));
    }

  
    @Transactional
    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new RuntimeException("Flight not found");
        }
        flightRepository.deleteById(id);
    }
}
	



