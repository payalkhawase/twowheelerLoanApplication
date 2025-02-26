package in.shriram.dreambiketwowheelerloan.application.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cibilscore {

	@Id
	private int cibilId;
	private int cibilScore;
	private Date cibilscoredDateTime = new Date();
	private String status;
	private String cibilRemark;
}
