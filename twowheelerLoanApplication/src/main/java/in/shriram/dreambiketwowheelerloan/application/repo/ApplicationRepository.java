package in.shriram.dreambiketwowheelerloan.application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.shriram.dreambiketwowheelerloan.application.model.Customer;

@Repository
public interface ApplicationRepository extends JpaRepository<Customer, Integer>{


	public List findByLoanStatus(String string);

	public Customer findByCustomerIdAndLoanStatus(int customerId, String string);


	public Customer findByCustomerEmailAndPassword(String customerEmail, String password);

	

//	@Query("SELECT c.customerId AS customerId, COUNT(l) AS ledgerCount " + "FROM Customer c JOIN c.led l GROUP BY c.customerId")
//	public List<Customer> findByLedLoanStatus(String string);




}
