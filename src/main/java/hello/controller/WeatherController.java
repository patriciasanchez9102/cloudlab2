package hello.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import hello.value.weather.*;
@RestController
public class WeatherController {
	private static final Logger log= LoggerFactory.getLogger(WeatherController.class);
	final String appId="e7ef46e4f75b686dd006df6ecaa575e4";
	
		 @GetMapping("/weather")
		 OpenWeather getWeather() {
			 RestTemplate restTemplate= new RestTemplate();
			 return restTemplate.getForObject(
					 "https://api.openweathermap.org/data/2.5/weather?q=Milan,it&appId=e7ef46e4f75b686dd006df6ecaa575e4"
					 ,OpenWeather.class);
		 }
		 @GetMapping("/weather/estrazione")
		 OpenWeather getWeather2() {
			 RestTemplate restTemplate= new RestTemplate();
			 String localita="Milan,it";
			 return restTemplate.getForObject(
					 "https://api.openweathermap.org/data/2.5/weather?q="+localita+"&appId="+appId
					 ,OpenWeather.class);
		 }
		
		 @GetMapping("/weather/{localita}")
		 OpenWeather getWeather3(@PathVariable String localita) {
			 RestTemplate restTemplate= new RestTemplate();
			 
			 String url="https://api.openweathermap.org/data/2.5/weather?q="+localita+"&appId="+appId;
			 return restTemplate.getForObject( url,OpenWeather.class);
		 }
		 
		 @GetMapping("/weather3/{localita}/{localita2}")
		 MeteoLocal getWeather3(@PathVariable String localita,@PathVariable String localita2) {
			 RestTemplate restTemplate= new RestTemplate();
			 String url1="https://api.openweathermap.org/data/2.5/weather?q="+localita+"&appId=e7ef46e4f75b686dd006df6ecaa575e4";
			 String url2="https://api.openweathermap.org/data/2.5/weather?q="+localita2+"&appId=e7ef46e4f75b686dd006df6ecaa575e4";
			 log.debug("Fetch url1: "+url1);
			 log.debug("Fetch url2: "+url2);
			 MeteoLocal m= new MeteoLocal();
			 OpenWeather f= restTemplate.getForObject(url1,OpenWeather.class);
			 OpenWeather r= restTemplate.getForObject(url2,OpenWeather.class);
			 m.setFirstcity(localita);
			 m.setSecondcity(localita2);
			 m.setFirsttime(f.getMain().getTemp()-273.15);
			 m.setSecondtime(r.getMain().getTemp()-273.15);
			 return m ;
		 }
}
