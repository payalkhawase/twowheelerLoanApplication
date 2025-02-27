package in.shriram.dreambiketwowheelerloan.utility;

import java.io.IOException;
import java.util.Base64;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ConvertByteArrayToString {

public String convertByteArrayToString(MultipartFile data) {
		
		String p=null;
		try {
			p=Base64.getEncoder().encodeToString(data.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
}
}
