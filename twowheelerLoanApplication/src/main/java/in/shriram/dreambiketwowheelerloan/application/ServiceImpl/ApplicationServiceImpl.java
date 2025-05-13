package in.shriram.dreambiketwowheelerloan.application.ServiceImpl;



import java.io.IOException;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import in.shriram.dreambiketwowheelerloan.application.model.AccountDetails;
import in.shriram.dreambiketwowheelerloan.application.model.Customer;

import in.shriram.dreambiketwowheelerloan.application.model.CustomerAddress;
import in.shriram.dreambiketwowheelerloan.application.model.LocalAddress;
import in.shriram.dreambiketwowheelerloan.application.model.PermanentAddress;
import in.shriram.dreambiketwowheelerloan.application.model.SanctionLetter;
import in.shriram.dreambiketwowheelerloan.application.repo.AccountDetailsRepo;
import in.shriram.dreambiketwowheelerloan.application.repo.ApplicationRepository;
import in.shriram.dreambiketwowheelerloan.application.repo.CustomerAddressRepo;
import in.shriram.dreambiketwowheelerloan.application.repo.DependentInformationRepo;
import in.shriram.dreambiketwowheelerloan.application.repo.LocalAddressRepo;
import in.shriram.dreambiketwowheelerloan.application.repo.PermanentAddressRepo;
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
	DependentInformationRepo dinfo; 


	@Autowired
	ObjectMapper ob;
	
	@Autowired
	AccountDetailsRepo acrepo;
	
	@Autowired
	CustomerAddressRepo caddrrepo;
	
	@Autowired
	LocalAddressRepo laddrrepo;
	
	@Autowired
	PermanentAddressRepo paddrrepo;

	@Override
	public Customer addCustomer(Customer customer) {

		AccountDetails ac=adr.save(customer.getAcdetails());
		customer.setAcdetails(ac);

		if(!customer.getAcdetails().equals(null))
		{
			AccountDetails aco = acrepo.save(customer.getAcdetails());
			customer.setAcdetails(aco);

		}
		

		if(!customer.getCustAddr().getLaddr().equals(null) || !customer.getCustAddr().getPaddr().equals(null))
		{
			CustomerAddress caddr = new CustomerAddress();
			
			LocalAddress laddr = laddrrepo.save(customer.getCustAddr().getLaddr());
			caddr.setLaddr(laddr);
			
			PermanentAddress paddr = paddrrepo.save(customer.getCustAddr().getPaddr());
			caddr.setPaddr(paddr);
			
			customer.setCustAddr(caddr);
		}
		
		if(!customer.getDepInfo().equals(null))
		{
			customer.setDepInfo(customer.getDepInfo());
		}
		

		Customer c= ar.save(customer);
		return c;
	}
	
	@Override
	public List<Customer> getAllCustomerDataSubmit() {
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
	public List<Customer> getCustomerVerified() {
		
		
		return ar.findByLoanStatus("Verified");

	}

	@Override

	public List getSanctionList(int customerId) {
		return ar.findAllByCustomerIdAndLoanStatus(customerId,"Sanctioned");
	}

	

	public Customer verify(String customerEmail, String password) {
		
		Customer cust=ar.findByCustomerEmailAndPassword(customerEmail,password);
		return cust;
		
	}

	@Override
	public Customer updateLoanStatus(int customerId, String loanStatus) {
		
		
		Customer cust=ar.findById(customerId).get();
		
		cust.setLoanStatus(loanStatus);
		
		return ar.save(cust);
		
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return ar.findAll();
	}

	

	


	


	


}
