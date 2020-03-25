package assignment_3.service;

import java.util.List;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import assignment_3.domain.*;

@Service
public class CityService {
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private WeatherService weatherService;
	
	public CityInfo getCityInfo(String cityName) {
		
		// Declare Variables
		CityInfo cityInfo;
		List<City> cityList;
		City city;
		Country country;
		String countryCode, countryName, time;
		double temp;
		
		// Pull data from object
		cityList = cityRepository.findByName(cityName);
		city = cityList.get(0);
		countryCode = city.getCountryCode();
		country = countryRepository.findByCode(countryCode);
		countryName = country.getName();
		temp = weatherService.getTempAndTime(cityName).temp;
		time = Long.toString(weatherService.getTempAndTime(cityName).time);
		cityInfo = new CityInfo(city, countryName, temp, time);
		
		return cityInfo; 
	}
	
    @Autowired
    private RabbitTemplate rabbitTemplate;
	
    @Autowired
    private FanoutExchange fanout;

    public void requestReservation( 
                   String cityName, 
                   String level, 
                   String email) {
		String msg  = "{\"cityName\": \""+ cityName + 
               "\" \"level\": \""+level+
               "\" \"email\": \""+email+"\"}" ;
		System.out.println("Sending message:"+msg);
		rabbitTemplate.convertSendAndReceive(
                fanout.getName(), 
                "",   // routing key none.
                msg);
	}


}
