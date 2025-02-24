package in.shriram.dreambiketwowheelerloan.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.shriram.dreambiketwowheelerloan.application.servicei.ApplicationServiceI;

@RestController
@RequestMapping("/apploan")
public class ApplicationController {

	@Autowired
	ApplicationServiceI asi;
}
