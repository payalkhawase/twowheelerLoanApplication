package in.shriram.dreambiketwowheelerloan.application.controller;

import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.shriram.dreambiketwowheelerloan.application.exception.InvalidFileTypeException;
import in.shriram.dreambiketwowheelerloan.application.exception.InvalidUserLoginException;


import in.shriram.dreambiketwowheelerloan.application.model.AllPersonalDocuments;
import in.shriram.dreambiketwowheelerloan.application.model.Customer;
import in.shriram.dreambiketwowheelerloan.application.model.Enquiry;
import in.shriram.dreambiketwowheelerloan.application.model.SanctionLetter;
import in.shriram.dreambiketwowheelerloan.application.servicei.ApplicationServiceI;

@RestController
@RequestMapping("/apploan")
public class ApplicationController {

	@Autowired
	ApplicationServiceI asi;
	
	@Autowired
	RestTemplate rt;
	
	@Autowired 
	ObjectMapper om;
	
	// Define the allowed image MIME types
    private static final List<String> ALLOWED_IMAGE_TYPES = Arrays.asList(
            "image/jpeg", "image/png", "image/jpg"
    );
	 
	@PostMapping("/addCustomer/{CustomerId}")
	public ResponseEntity<Customer> addCustomer(@PathVariable ("CustomerId") int CustomerId,
			@RequestPart ("data") String jsonData,
			@RequestPart ("addressProof") MultipartFile addressProof,
			@RequestPart ("panCard") MultipartFile panCard,
			@RequestPart ("IncomeTax") MultipartFile IncomeTax,
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
		{
			if (!addressProof.getContentType().equals("application/pdf")) {
	            throw new InvalidFileTypeException("Only PDF files are allowed for address proof");
	        }
			else
			{
				apdoc.setAddressProof(addressProof.getBytes());
			}
		}
		if(!panCard.isEmpty())
		{	
			if (!panCard.getContentType().equals("application/pdf")) {
	            throw new InvalidFileTypeException("Only PDF files are allowed for Pan Card");
	        }
			else
			{
			apdoc.setPanCard(panCard.getBytes());
			}
		}
		if(!IncomeTax.isEmpty())
		{
			if (!IncomeTax.getContentType().equals("application/pdf")) {
	            throw new InvalidFileTypeException("Only PDF files are allowed for IncomeTax");
	        }
			else
			{
				apdoc.setIncomeTax(IncomeTax.getBytes());
			}
		}
		if(!addharCard.isEmpty())
		{
			if (!addharCard.getContentType().equals("application/pdf")) {
	            throw new InvalidFileTypeException("Only PDF files are allowed for addhar Card");
	        }
			else
			{
				apdoc.setAddharCard(addharCard.getBytes());

			}
		}
			
		if(!photo.isEmpty())
		{
			if (!ALLOWED_IMAGE_TYPES.contains(photo.getContentType())) {
	            throw new InvalidFileTypeException("Only image files (JPEG, PNG, JPG) are allowed for photo");
	        }
			else {
				apdoc.setPhoto(photo.getBytes());
			}
		}
		
		if(!signature.isEmpty())
		apdoc.setSignature(signature.getBytes());
		if(!bankCheque.isEmpty())
		{
			if (!bankCheque.getContentType().equals("application/pdf")) {
	            throw new InvalidFileTypeException("Only PDF files are allowed for bank Cheque");
	        }
			else
			{
				apdoc.setBankCheque(bankCheque.getBytes());

			}
		}
		if(!salarySlips.isEmpty())
		{
			if (!salarySlips.getContentType().equals("application/pdf")) {
	            throw new InvalidFileTypeException("Only PDF files are allowed for salary Slips");
	        }
			else
			{
				apdoc.setSalarySlips(salarySlips.getBytes());
			}
		}
		
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
	
	


   @GetMapping("/getaCustomer/{customerId}")

	    public ResponseEntity<Customer> getcustomer(@PathVariable("customerId") int customerId) {
		   
		Customer cu= asi.getcustomer(customerId);
		return new ResponseEntity<Customer>(cu,HttpStatus.OK);
	}


	@GetMapping("/getCustomerVerified/{customerId}")

	    public Customer getCustomerVerified(@PathVariable("customerId") int customerId) {
		   
		Customer cu= asi.getCustomerVerified(customerId);
		return cu;
	}
	


	@GetMapping("/verifyALogin/{customerEmail}/{password}")
	public ResponseEntity<Customer> verifyALogin(@PathVariable("customerEmail") String customerEmail,
			@PathVariable("password") String password){
		
			Customer cust=asi.verify(customerEmail,password);
			if(cust!=null) {
			return new ResponseEntity<Customer>(cust,HttpStatus.OK);
			}
			else {
				
				throw new InvalidUserLoginException("Sorry, user not found!");
			}
			
	}
	
	@PutMapping("/updateLoanStatus/{customerId}/{loanStatus}")
	public ResponseEntity<Customer> updateLoanStatus(@PathVariable("customerId") int customerId,
			@PathVariable("loanStatus") String loanStatus){
		
		Customer cust=asi.updateLoanStatus(customerId,loanStatus);
		
		return new ResponseEntity<Customer>(cust,HttpStatus.OK);
	}

	@GetMapping("/getSanctionList/{customerId}")
	public ResponseEntity<Customer> getSanctionList(@PathVariable("customerId") int customerId)
	{
		Customer list = asi.getSanctionList(customerId);
		return new ResponseEntity<Customer>(list,HttpStatus.OK);

	}



}
