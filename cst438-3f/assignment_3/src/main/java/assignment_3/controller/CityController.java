package assignment_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import assignment_3.domain.CityInfo;
import assignment_3.service.CityService;

@Controller
public class CityController {
	
	@Autowired
	private CityService cityService;
	
	private String testName;
	
	@GetMapping("/cities/{city}")
	public String getCityInfo(@PathVariable("city") String cityName, Model model) {
		
		// Create cityInfo object to store request in
		CityInfo cityInfo = cityService.getCityInfo(cityName);

		// Add cityInfo object to the output template (HTML)
		model.addAttribute("cityInfo", cityInfo);
		
		testName = cityName;
		
		return "showcity";
	} 
	
	@PostMapping("/cities/reservation")
	public String createReservation(
			@RequestParam("level") String level, 
			@RequestParam("email") String email, 
			Model model) {
	   
	   model.addAttribute("theName", testName);
	   model.addAttribute("vacationType", level);
	   model.addAttribute("email", email);
	   
		cityService.requestReservation(testName, level, email);
		return "request_reservation";
	}

	
}
