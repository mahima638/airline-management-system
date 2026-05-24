package TY_PROJECT.Programs.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



import TY_PROJECT.Programs.service.FlightService;
import TY_PROJECT.ProgramsController.entity.Flight;

@RestController
@RequestMapping("/api/flights")
public class FlightApiController {
	
	@Autowired
	public FlightService flightservice;
	
	@GetMapping
	public ResponseEntity<List<Flight>> getAllFlights() {
		List<Flight> flights = flightservice.getAllFlights();
		return ResponseEntity.ok(flights);
	}
	
	
	 @GetMapping("/{id}")
	 public ResponseEntity<Flight> getFlightBtId(@PathVariable Long id) {
		 Flight flight = flightservice.getFlightById(id);
		 return ResponseEntity.ok(flight);
	 }
	 
	 @GetMapping("/search")
	 public ResponseEntity<List<Flight>> searchFlights(
			 @RequestParam String source,
			 @RequestParam String destination,
			 @RequestParam String date) {
		 List<Flight> flights = flightservice.findFlights(source, destination, date);
		 return ResponseEntity.ok(flights);
	 }
}
