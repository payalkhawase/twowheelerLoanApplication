package in.shriram.dreambiketwowheelerloan.application.ServiceImpl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.shriram.dreambiketwowheelerloan.application.model.AccountDetails;
import in.shriram.dreambiketwowheelerloan.application.model.Customer;
import in.shriram.dreambiketwowheelerloan.application.repo.AccountDetailsRepo;
import in.shriram.dreambiketwowheelerloan.application.repo.ApplicationRepository;
import in.shriram.dreambiketwowheelerloan.application.servicei.ApplicationServiceI;

@Service
public class ApplicationServiceImpl implements ApplicationServiceI{

	@Autowired
	ApplicationRepository ar;

	@Autowired
	AccountDetailsRepo adr;
	
	//@Autowired
   // LoanDisbursement ld;

	@Autowired
	ObjectMapper ob;
	
	@Autowired
	AccountDetailsRepo acrepo;

	@Override
	public Customer addCustomer(Customer customer) {

		AccountDetails ac=adr.save(customer.getAcdetails());
		customer.setAcdetails(ac);

		
		if(!customer.getAcdetails().equals(null))
		{
			AccountDetails aco = acrepo.save(customer.getAcdetails());
			customer.setAcdetails(aco);

		}
		

		Customer c= ar.save(customer);
		return c;
	}
	
	@Override
	public List getAllCustomerDataSubmit() {
		// TODO Auto-generated method stub
		return ar.findByLoanStatus("Submit");
	}


	@Override
	public void deleteCustomer(int customerId) {
		
		ar.deleteById(customerId);
	}


	@Override
	public Customer updateCustomer(Customer customer) {
		Customer cu=ar.save(customer);
		return cu;
	}


	@Override
	public Customer getcustomer(int customerId) {
		
		return ar.findById(customerId).get();

		
	}

	@Override
	public Customer getCustomerVerified(int customerId) {
		Customer co = new Customer();
		co = ar.findById(customerId).get();
		
		if(co.getLoanStatus().equals("Verified"))
		{
			return co;
		}
		
		return null;

		

	}

	


}
