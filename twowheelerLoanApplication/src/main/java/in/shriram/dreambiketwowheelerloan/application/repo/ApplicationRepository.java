package in.shriram.dreambiketwowheelerloan.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shriram.dreambiketwowheelerloan.application.model.Customer;

@Repository
public interface ApplicationRepository extends JpaRepository<Customer, Integer>{

}
