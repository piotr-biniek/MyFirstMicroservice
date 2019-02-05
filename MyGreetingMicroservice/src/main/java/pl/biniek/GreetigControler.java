package pl.biniek;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetigControler {
	private final static String TEMPLATE = "Helloł %s";
	private static AtomicLong licznik = new AtomicLong();
	
	
	//@RequestMapping(value="/greeting", method = RequestMethod.GET)
	//alternatywnie
	@GetMapping("/")
	public Greeting greeting(@RequestParam( required=false,defaultValue="Visitor") String name) {// wiązanie po nazwie zmiennej name!!!!!!!
		
		return new Greeting(licznik.incrementAndGet(),String.format(TEMPLATE, name));
		
	}
	
	
	
	
}
