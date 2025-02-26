package in.shriram.dreambiketwowheelerloan.application.controller;

import in.shriram.dreambiketwowheelerloan.application.model.Cibilscore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Enquiry {

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerId;
	private String firstname;
	private String lastName;
	private String address; 
	private String city;
	private int age;
	private String email;
	private String mobileNo;
	private long alternateMobno;
	private String pancardNo;
	private String adharcardNo;
	private String enquiryStatus="Pending";
	
	@OneToOne(cascade = CascadeType.ALL)
	private Cibilscore cb;
	
}
