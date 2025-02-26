package in.shriram.dreambiketwowheelerloan.application.ServiceImpl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.shriram.dreambiketwowheelerloan.application.model.AllPersonalDocuments;
import in.shriram.dreambiketwowheelerloan.application.model.Customer;
import in.shriram.dreambiketwowheelerloan.application.model.DependentInformation;
import in.shriram.dreambiketwowheelerloan.application.repo.AllPersonalDocumentsRepo;
import in.shriram.dreambiketwowheelerloan.application.repo.ApplicationRepository;
import in.shriram.dreambiketwowheelerloan.application.servicei.ApplicationServiceI;

@Service
public class ApplicationServiceImpl implements ApplicationServiceI{

	@Autowired
	ApplicationRepository ar;
	
	@Autowired
	AllPersonalDocumentsRepo apdr;
	
	@Autowired
	ObjectMapper ob;
	
//	@Autowired
//	DependentInformationRepo dinfo;

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
	public Customer saveFile(String json, MultipartFile addressProof, MultipartFile incomeTax, MultipartFile addharCard,
			MultipartFile photo, MultipartFile signature, MultipartFile bankCheque, MultipartFile salarySlips) {
		try {
			AllPersonalDocuments personaldoc= new AllPersonalDocuments();
			Customer pdoc=null;
			pdoc= ob.readValue(json, Customer.class);
			
		byte[] adrProof= addressProof.getBytes();
		byte[] iTax=  incomeTax.getBytes();
		byte[] adrCard= addharCard.getBytes();
		byte[] ph= photo.getBytes();
		byte[] sign= signature.getBytes();
		byte[] bcheck= bankCheque.getBytes();
		byte[] salSlip= salarySlips.getBytes();
		
		personaldoc.setAddressProof(adrProof);
		personaldoc.setIncomeTax(iTax);
		personaldoc.setAddharCard(adrCard);
		personaldoc.setPhoto(ph);
		personaldoc.setSignature(sign);
		personaldoc.setBankCheque(bcheck);
		personaldoc.setSalarySlips(salSlip);
		
		pdoc.setPersonalDoc(personaldoc);
		
//		pdoc.equals(adrProof);
//		pdoc.equals(iTax);
//		pdoc.equals(adrCard);
//		pdoc.equals(ph);
//		pdoc.equals(sign);
//		pdoc.equals(bcheck);
//		pdoc.equals(salSlip);
//		
	
//		pdoc.setAddressProof(adrProof);
//		pdoc.setIncomeTax(iTax);
//		pdoc.setAddharCard(adrCard);
//		pdoc.setPhoto(ph);
//		pdoc.setSignature(sign);
//		pdoc.setBankCheque(bcheck);
//		pdoc.setSalarySlips(salSlip);
		Customer alldoc= ar.save(pdoc);
		
		
		return alldoc;
		
	} catch (IOException e) {
		
		e.printStackTrace();
	}
		return null;
	}
	
}
