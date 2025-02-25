package in.shriram.dreambiketwowheelerloan.application.ServiceImpl;

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
	
//	@Autowired
//	DependentInformationRepo dinfo;

	@Override
	public Customer addCustomer(Customer customer) {
		
		Customer c= ar.save(customer);
		return c;
	}

	
}
