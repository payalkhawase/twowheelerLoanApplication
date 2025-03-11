package in.shriram.dreambiketwowheelerloan.application.globalexceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.shriram.dreambiketwowheelerloan.application.dto.CustomerResponse;
import in.shriram.dreambiketwowheelerloan.application.exception.InvalidFileTypeException;
import in.shriram.dreambiketwowheelerloan.application.exception.InvalidPhotoTypeException;
import in.shriram.dreambiketwowheelerloan.application.exception.InvalidUserLoginException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(InvalidUserLoginException.class)
	public ResponseEntity<CustomerResponse> incorrectPassword(InvalidUserLoginException ip){
		
		String msg=ip.getMessage();
		
		CustomerResponse cr=new CustomerResponse(msg, new Date());
	
		return new ResponseEntity<CustomerResponse>(cr,HttpStatus.BAD_REQUEST);
	}
	
	 @ExceptionHandler(InvalidFileTypeException.class)
	 public ResponseEntity<String> handleInvalidFileTypeException(InvalidFileTypeException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	 }
	 
	 @ExceptionHandler(InvalidPhotoTypeException.class)
	 public ResponseEntity<String> handleInvalidPhotoTypeException(InvalidPhotoTypeException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	 }

}
