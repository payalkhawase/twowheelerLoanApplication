package in.shriram.dreambiketwowheelerloan.application.servicei;

import java.util.List;


import org.springframework.web.multipart.MultipartFile;

import in.shriram.dreambiketwowheelerloan.application.model.AllPersonalDocuments;

import in.shriram.dreambiketwowheelerloan.application.model.Customer;


public interface ApplicationServiceI {

	
	public Customer addCustomer(Customer customer);
	
	public Customer updateCustomer(Customer customer);
	public void deleteCustomer(int customerId);

	public List getAllCustomerDataSubmit();

	public Customer getcustomer(int customerId);

	public Customer getCustomerVerified(int customerId);

	



}
