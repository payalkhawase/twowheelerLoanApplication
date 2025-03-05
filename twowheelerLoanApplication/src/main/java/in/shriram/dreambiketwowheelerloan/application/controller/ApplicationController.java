package in.shriram.dreambiketwowheelerloan.application.controller;

import java.util.List;


import org.bouncycastle.asn1.x509.sigi.PersonalData;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.shriram.dreambiketwowheelerloan.application.model.AllPersonalDocuments;
import in.shriram.dreambiketwowheelerloan.application.model.Customer;

import in.shriram.dreambiketwowheelerloan.application.model.Enquiry;

import in.shriram.dreambiketwowheelerloan.application.servicei.ApplicationServiceI;
import jakarta.persistence.Entity;

@RestController
@RequestMapping("/apploan")
public class ApplicationController {

	@Autowired
	ApplicationServiceI asi;
	
	@Autowired
	RestTemplate rt;
	
	@Autowired 
	ObjectMapper om;
	 
	@PostMapping("/addCustomer/{CustomerId}")
	public ResponseEntity<Customer> addCustomer(@PathVariable ("CustomerId") int CustomerId,
			@RequestPart ("data") String jsonData,
			@RequestPart ("addressProof") MultipartFile addressProof,
			@RequestPart ("panCard") MultipartFile panCard,
			//@RequestPart ("IncomeTax") MultipartFile IncomeTax,
			@RequestPart ("addharCard") MultipartFile addharCard,
			@RequestPart ("photo") MultipartFile photo,
			@RequestPart ("signature") MultipartFile signature,
			@RequestPart ("bankCheque") MultipartFile bankCheque,
			@RequestPart ("salarySlips") MultipartFile salarySlips) throws Exception{
		
		Enquiry e = rt.getForObject("http://localhost:7777/enq/enquiry/"+CustomerId, Enquiry.class);
		
		Customer customer = om.readValue(jsonData, Customer.class);
		
		customer.setCustomerName(e.getFirstname()+" "+e.getLastName());
		customer.setCustomerAge(e.getAge());
		customer.setCustomerEmail(e.getEmail());
		customer.setCustomerMobileNumber(e.getMobileNo());
		customer.setCustomerAdditionalMobileNumber(e.getAlternateMobno());
		customer.setCibil(e.getCibil());
		customer.setPassword(e.getPassword());
		
		AllPersonalDocuments apdoc = new AllPersonalDocuments();
		if(!addressProof.isEmpty())
		apdoc.setAddressProof(addressProof.getBytes());
		if(!panCard.isEmpty())
		apdoc.setPanCard(panCard.getBytes());
		//if(!IncomeTax.isEmpty())
		//apdoc.setIncomeTax(IncomeTax.getBytes());
		if(!addharCard.isEmpty())
		apdoc.setAddharCard(addharCard.getBytes());
		if(!photo.isEmpty())
		apdoc.setPhoto(photo.getBytes());
		if(!signature.isEmpty())
		apdoc.setSignature(signature.getBytes());
		if(!bankCheque.isEmpty())
		apdoc.setBankCheque(bankCheque.getBytes());
		if(!salarySlips.isEmpty())
		apdoc.setSalarySlips(salarySlips.getBytes());
		
		customer.setPersonalDoc(apdoc);
		Customer  info= asi.addCustomer(customer);
		
		return new ResponseEntity<Customer>(info,HttpStatus.OK);
		
	}
	
	
	@PutMapping("/upadtedata")
    public ResponseEntity<Customer> updateCustomerInfo(@RequestBody Customer customer){
		
		Customer c= asi.updateCustomer(customer);
		return new ResponseEntity<Customer>(c,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{customerId}")
    public void deleteCustomerInfo(@PathVariable("customerId") int customerId){
		
		 asi.deleteCustomer(customerId);
		
	}

	@GetMapping("/getAllCustomerDataSubmit")
	public ResponseEntity<List> getAllCustomerDataSubmit() {
		List list = asi.getAllCustomerDataSubmit();
		return new ResponseEntity<List>(list,HttpStatus.OK);
	}


	@GetMapping("/getCustomer/{customerId}")
	    public ResponseEntity<Customer> getcustomer(@PathVariable("customerId") int customerId) {
		   
		Customer cu= asi.getcustomer(customerId);
		return new ResponseEntity<Customer>(cu,HttpStatus.OK);
	}

	@GetMapping("/getCustomerVerified/{customerId}")
	    public Customer getCustomerVerified(@PathVariable("customerId") int customerId) {
		   
		Customer cu= asi.getCustomerVerified(customerId);
		return cu;
	}
	
	
}
