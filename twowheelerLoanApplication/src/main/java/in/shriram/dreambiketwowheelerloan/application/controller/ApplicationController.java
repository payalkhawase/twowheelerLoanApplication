package in.shriram.dreambiketwowheelerloan.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shriram.dreambiketwowheelerloan.application.model.Customer;
import in.shriram.dreambiketwowheelerloan.application.model.DependentInformation;
import in.shriram.dreambiketwowheelerloan.application.servicei.ApplicationServiceI;

@RestController
@RequestMapping("/apploan")
public class ApplicationController {

	@Autowired
	ApplicationServiceI asi;
	
	
	@PostMapping("/addCustomerInfo")
	public ResponseEntity<Customer> addFamilyInfo(@RequestBody Customer customer){
		
		Customer  info= asi.addCustomer(customer);
		return new ResponseEntity<Customer>(info,HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomerDataSubmit")
	public ResponseEntity<List> getAllCustomerDataSubmit() {
		List list = asi.getAllCustomerDataSubmit();
		return new ResponseEntity<List>(list,HttpStatus.OK);
	}

	
}
