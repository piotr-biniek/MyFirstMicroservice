package pl.biniek.service;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
//import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javassist.NotFoundException;
import pl.biniek.model.Event;
import pl.biniek.repository.EventRepo;
@Service
public class EventServiceImp implements EventService {
	
	private static final Logger LOGGER =LoggerFactory.getLogger(EventServiceImp.class);

	@Autowired
	EventRepo eventRepository;
	
	@Override
	public Iterable<Event> getAllEvents() {
		return eventRepository.findAll();
		
	}

	@Override
	public Event put(Event singleEvent) {
		LOGGER.info("CREATE form post started");
	//	return singleEvent;
	return eventRepository.save(singleEvent);
	}

	@Override
	public Event getEvent(Long id) throws NotFoundException {
		Optional<Event> opt = eventRepository.findById(id);
		if(opt.isPresent()) return opt.get();
		else throw new NotFoundException("Entity not found");
		
	}

	@Override
	//public boolean deleteEvent(Long id) {
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	//return !eventRepository.existsById(id);
	}



	
	
}
