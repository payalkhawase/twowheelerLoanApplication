package in.shriram.dreambiketwowheelerloan.application.servicei;

import java.util.List;



import org.springframework.web.multipart.MultipartFile;


import in.shriram.dreambiketwowheelerloan.application.model.AllPersonalDocuments;

import in.shriram.dreambiketwowheelerloan.application.model.Customer;
import in.shriram.dreambiketwowheelerloan.application.model.SanctionLetter;


public interface ApplicationServiceI {

	
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(int customerId);

	public List<Customer> getAllCustomerDataSubmit();

	public Customer getcustomer(int customerId);

	public List<Customer> getCustomerVerified();


	public List getSanctionList(int customerId);

	public Customer verify(String customerEmail, String password);

	public Customer updateLoanStatus(int customerId, String loanStatus);

	public List<Customer> getAllCustomer();

	public Customer getSingleCustomerVerified(int customerId);

	public List getSanctionedList();

	public List<Customer> getSanctionedcustomer();

	


	


	



}
