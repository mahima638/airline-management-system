package TY_PROJECT.ProgramsController.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Flight_details")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public LocalDate getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public LocalTime getDeparture_time() {
		return departure_time;
	}
	public void setDeparture_time(LocalTime departure_time) {
		this.departure_time = departure_time;
	}
	public LocalTime getArrival_time() {
		return arrival_time;
	}
	public void setArrival_time(LocalTime arrival_time) {
		this.arrival_time = arrival_time;
	}
	@CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

	private String source;
    private String destination;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private LocalTime departure_time;
    private LocalTime arrival_time;
}