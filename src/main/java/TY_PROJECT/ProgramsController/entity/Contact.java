package TY_PROJECT.ProgramsController.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contact_messages")
public class Contact {

	
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String name;
	    public Contact(Long id, String name, String email, String subject, String message) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.subject = subject;
			this.message = message;
		}
	    public Contact() {
	    }
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getSubject() {
			return subject;
		}
		public void setSubject(String subject) {
			this.subject = subject;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		private String email;
	    private String subject;
	    private String message;
	    private String status = "Pending"; 
	    public Contact(String status, String adminResponse) {
			super();
			this.status = status;
			this.adminResponse = adminResponse;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getAdminResponse() {
			return adminResponse;
		}
		public void setAdminResponse(String adminResponse) {
			this.adminResponse = adminResponse;
		}
		private String adminResponse;
		
	}

