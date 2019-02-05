package pl.biniek.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javassist.NotFoundException;
import pl.biniek.model.Event;

public interface EventService {
	
	  

     	Iterable<Event> getAllEvents();	
		Event put(Event singleEvent);
	
		Event getEvent(Long id) throws NotFoundException ;
	//	boolean deleteEvent (Long id);
		void deleteEvent (Long id);
	   		
		

	}

	

