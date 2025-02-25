package in.shriram.dreambiketwowheelerloan.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class TwowheelerLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TwowheelerLoanApplication.class, args);
	}

	
	
}
