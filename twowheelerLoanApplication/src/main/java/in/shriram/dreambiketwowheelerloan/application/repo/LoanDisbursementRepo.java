package in.shriram.dreambiketwowheelerloan.application.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.shriram.dreambiketwowheelerloan.application.model.LoanDisbursement;
@Repository
public interface LoanDisbursementRepo extends JpaRepository<LoanDisbursement, Integer>{

}
