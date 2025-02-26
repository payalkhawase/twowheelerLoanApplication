package in.shriram.dreambiketwowheelerloan.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import in.shriram.dreambiketwowheelerloan.application.model.AllPersonalDocuments;
import in.shriram.dreambiketwowheelerloan.application.model.Customer;
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
	
	
		@PostMapping("/addAllInfor")
		public ResponseEntity<Customer> addAllInformation(@RequestPart ("data") String jsonData,
				@RequestPart ("addressProof") MultipartFile addressProof,
				@RequestPart ("IncomeTax") MultipartFile IncomeTax,
				@RequestPart ("addharCard") MultipartFile addharCard,
				@RequestPart ("photo") MultipartFile photo,
				@RequestPart ("signature") MultipartFile signature,
				@RequestPart ("bankCheque") MultipartFile bankCheque,
				@RequestPart ("salarySlips") MultipartFile salarySlips)
		
		{
			
			Customer info= asi.saveFile(jsonData, addressProof, IncomeTax, addharCard, photo, signature,bankCheque, salarySlips);
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
	
	
	
}
