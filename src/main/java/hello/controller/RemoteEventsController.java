package hello.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import hello.InvalidCountryCodeException;
import hello.model.Event;
import hello.repository.EventsRepository;

public class RemoteEventsController {
	private static final Logger log= LoggerFactory.getLogger(RemoteEventsController.class);
	private Map <String,String> urls= new HashMap<>();
	@Autowired
	EventsRepository eventsRepository;
	
	@GetMapping("/remote/events/{country}")
	public List<Event> fetchRemoteEvents(@PathVariable String country){
		RestTemplate restTemplate= new RestTemplate();
		initUrls();
		
		String url = urls.get(country);
		log.info("using url "+url);
		
			if(url != null) {
				ResponseEntity<List<Event>> response= restTemplate.exchange(
						url,
						HttpMethod.GET,
						null,
						new ParameterizedTypeReference<List<Event>>() {
						});
		List<Event> events = response.getBody();
		return events;
			}else {
		throw new InvalidCountryCodeException();
	}
	}
	private void initUrls() {
		urls.put("us", "http://alfo.cloud-lab.it/hello/events");
		urls.put("fr", "http://pasquale.cloud-lab.it/hello/events");
		urls.put("it", "http://danilo.cloud-lab.it/hello/events");
		urls.put("nl", "http://francesco.cloud-lab.it/hello/events");
		urls.put("nz", "http://stefano.cloud-lab.it/hello/events");
		urls.put("ma", "http://souad.cloud-lab.it/hello/events");
		urls.put("no", "http://luca.cloud-lab.it/hello/events");
		urls.put("ph", "http://rose.cloud-lab.it/hello/events");
		urls.put("pe", "http://ana.cloud-lab.it/hello/events");

	}
}
