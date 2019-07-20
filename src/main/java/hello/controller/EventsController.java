package hello.controller;

import hello.EventNotFoundException;
import hello.model.Event;
import hello.repository.EventsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;




import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class EventsController {

    @Autowired
    EventsRepository eventsRepository;

    @GetMapping("/events")
    public List<Event> fetchEvents() {
        return eventsRepository.findAll();
    }
    @GetMapping("/event/by/{id}")
    public Event byId(@PathVariable long id) {
   	 return eventsRepository.findById(id).get();
   }
    
    @PostMapping("/event/new")
    public List<Event> addEvent(@RequestBody Event e ){
    	eventsRepository.save(e);
    	return eventsRepository.findAll();
    }
    
    @PutMapping("/event/put/{id}")
    public List<Event> putEvent(@RequestBody Event e,@PathVariable Long id){
    	Optional<Event> list=eventsRepository.findById(id);
    	if(!list.isPresent()) {
			throw new EventNotFoundException();
		}
    	list.get().setId(e.getId());
    	list.get().setName(e.getName());
    	eventsRepository.save(list.get());
    	return eventsRepository.findAll();
    }
    
    @DeleteMapping("/event/delete/{id}")
    public List<Event> deleteEvent(@PathVariable Long id){
    	eventsRepository.deleteById(id);
    	return eventsRepository.findAll();
    }
    
}

