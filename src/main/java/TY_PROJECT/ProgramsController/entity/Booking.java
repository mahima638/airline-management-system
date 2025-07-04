package TY_PROJECT.ProgramsController.entity;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Long flightid;
	private String name;
	@Past(message = "Date of Birth must be in the past")
	private Date dob;
    private String gender;
    private String nationality;
    
    @Size(min =10, max=10, message="Phone number must be 10 digits")
    @Pattern(regexp="\\d{10}", message="Only numbers are allowed")
 
    private String  phoneno;
    private String  departure_cities;
    private String  arrival_cities;
    private String tripType;

    @Column(unique = true)
    @Email(message="please enter a valid email adress")
    //@Pattern(regexp="^[a-zA-Z0-9._%+-]+@gmail.com\\.com$", message ="Only Gmail addresses are allowed")
    private String email;
    @Pattern(regexp="\\d{10}", message="Only numbers are allowed")
  
    private String passportNumber;
   
    private String password;
    @Column(name = "booking_date")
    private LocalDate bookingDate;
    
    

  
    public Booking(String email, String passportNumber, String password) {
		super();
		this.email = email;
		this.passportNumber = passportNumber;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassportNumber() {
		return passportNumber;
	}
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}


	private String  seat_prefered;
    private int    no_of_bags;
    private  String special_request;
    
    public Booking() {
    	
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getFlightid() {
		return flightid;
	}
	public void setFlightid(Long flightid) {
		this.flightid = flightid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getDeparture_cities() {
		return departure_cities;
	}
	public void setDeparture_cities(String departure_cities) {
		this.departure_cities = departure_cities;
	}
	public String getArrival_cities() {
		return arrival_cities;
	}
	public void setArrival_cities(String arrival_cities) {
		this.arrival_cities = arrival_cities;
	}
	public String getSeat_prefered() {
		return seat_prefered;
	}
	public void setSeat_prefered(String seat_prefered) {
		this.seat_prefered = seat_prefered;
	}
	public int getNo_of_bags() {
		return no_of_bags;
	}
	public void setNo_of_bags(int no_of_bags) {
		this.no_of_bags = no_of_bags;
	}
	

	public String getSpecial_request() {  
	    return special_request;  
	}

	public void setSpecial_request(String special_request) {  
	    this.special_request = special_request;  
	}
	public String getTripType() {
	    return tripType;
	}

	public void setTripType(String tripType) {
	    this.tripType = tripType;
	}


	public Booking(Long id, Long flightid, String name, Date dob, String gender, String nationality, String phoneno,
			String departure_cities, String arrival_cities, String seat_prefered, int no_of_bags,
			String special_request) {
		super();
		this.id = id;
		this.flightid = flightid;
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.nationality = nationality;
		this.phoneno = phoneno;
		this.departure_cities = departure_cities;
		this.arrival_cities = arrival_cities;
		this.seat_prefered = seat_prefered;
		this.no_of_bags = no_of_bags;
		this.special_request = special_request;
	}
  
}
