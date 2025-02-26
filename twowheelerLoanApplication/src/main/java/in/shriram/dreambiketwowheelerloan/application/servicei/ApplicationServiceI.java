package in.shriram.dreambiketwowheelerloan.application.servicei;

import java.util.List;

import in.shriram.dreambiketwowheelerloan.application.model.Customer;
import in.shriram.dreambiketwowheelerloan.application.model.DependentInformation;

public interface ApplicationServiceI {

	
	public Customer addCustomer(Customer customer);


	public Customer updateCustomer(Customer customer);

	public void deleteCustomer(int customerId);

	public List getAllCustomerDataSubmit();

	public Customer getcustomer(int customerId);


}
