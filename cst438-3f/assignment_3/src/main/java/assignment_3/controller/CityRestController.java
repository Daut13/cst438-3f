package assignment_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import assignment_3.domain.CityInfo;
import assignment_3.service.CityService;

@RestController
public class CityRestController {
	
	@Autowired
	private CityService cityService;

	// Rest get request. Returns object in JSON format
	@GetMapping("/api/cities/{city}")
	public ResponseEntity<CityInfo> getWeather(@PathVariable("city") String cityName) {
	  
		// Create cityInfo object to store data
		CityInfo cityInfo = cityService.getCityInfo(cityName);
		
		// If null, return NOT_FOUND error
		if (cityInfo == null) {
			return new ResponseEntity<CityInfo>(HttpStatus.NOT_FOUND);
		}
		
		//Else, return the response entity with cityInfo object to be parsed to JSON
		return new ResponseEntity<CityInfo>(cityInfo, HttpStatus.OK);
	}
	
}
