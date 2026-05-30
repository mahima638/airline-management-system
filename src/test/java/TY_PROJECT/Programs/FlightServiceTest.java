package TY_PROJECT.Programs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import TY_PROJECT.Programs.exception.FlightNotFoundException;
import TY_PROJECT.Programs.repository.FlightRepository;
import TY_PROJECT.Programs.service.FlightService;
import TY_PROJECT.ProgramsController.entity.Flight;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

    @Mock
    private FlightRepository flightRepository;

    @InjectMocks
    private FlightService flightService;

    // Test 1 - Get all flights returns list
    @Test
    public void testGetAllFlights() {
        Flight flight1 = new Flight();
        Flight flight2 = new Flight();
        when(flightRepository.findAll()).thenReturn(Arrays.asList(flight1, flight2));

        List<Flight> result = flightService.getAllFlights();

        assertEquals(2, result.size());
    }

    // Test 2 - Get flight by valid ID
    @Test
    public void testGetFlightById_Success() {
        Flight flight = new Flight();
        flight.setId(1L);
        when(flightRepository.findById(1L)).thenReturn(Optional.of(flight));

        Flight result = flightService.getFlightById(1L);

        assertEquals(1L, result.getId());
    }

    // Test 3 - Get flight by invalid ID throws exception
    @Test
    public void testGetFlightById_NotFound() {
        when(flightRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(FlightNotFoundException.class, () -> {
            flightService.getFlightById(999L);
        });
    }

    // Test 4 - Delete flight that doesn't exist throws exception
    @Test
    public void testDeleteFlight_NotFound() {
        when(flightRepository.existsById(999L)).thenReturn(false);

        assertThrows(FlightNotFoundException.class, () -> {
            flightService.deleteFlight(999L);
        });
    }
}