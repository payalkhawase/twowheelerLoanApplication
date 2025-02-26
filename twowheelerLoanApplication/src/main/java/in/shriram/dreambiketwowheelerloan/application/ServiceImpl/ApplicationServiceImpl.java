package in.shriram.dreambiketwowheelerloan.application.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.shriram.dreambiketwowheelerloan.application.model.Customer;
import in.shriram.dreambiketwowheelerloan.application.model.DependentInformation;
import in.shriram.dreambiketwowheelerloan.application.repo.ApplicationRepository;
import in.shriram.dreambiketwowheelerloan.application.servicei.ApplicationServiceI;

@Service
public class ApplicationServiceImpl implements ApplicationServiceI{

	@Autowired
	ApplicationRepository ar;
	

	@Override
	public Customer addCustomer(Customer customer) {
		
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
		Optional<Customer> op=ar.findById(customerId);
		if(op.isPresent()) {
			Customer cs=op.get();
			return cs;
		}
		return null;
	}

	


	

	
}
