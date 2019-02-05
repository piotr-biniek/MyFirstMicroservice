package pl.biniek.service;

import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;
import pl.biniek.model.Event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
public class MyRestControlere {

	@Autowired
	private EventServiceImp service;

	/*
	 * We need to inform Spring MVC which method in our controller maps to which
	 * endpoint in our application. We can do this with the @RequestMapping
	 * annotation on a method. With the @RequestMapping, Spring knows that this is
	 * an endpoint. The value parameter is the path under which this method is made
	 * available, and the method parameter defines that we only accept HTTP Post
	 * request. Both parameters are set on the annotation.
	 * The @RequestMapping has a produces parameter that defines which format the
	 * endpoint will return. Using the values of the @RequestMapping annotation and
	 * by analyzing the request, Spring MVC decides which converter to use.
	 */
	@RequestMapping(value = "/event", method = RequestMethod.POST) // and more produces, consumes itp
	// można użyć zamiast tego //@PostMapping (value = "/event")
	@ResponseStatus(value = HttpStatus.CREATED)
	/*
	 *  jaką odpowiedż dostanie strona wołająca 
	 * @ResponseStatus is an annotation to change the HTTP status code of a
	 * response. In REST APIs it is usually a 201 - Created instead of a regular 200
	 * - OK when we created a new instance of our model.
	 */
	
	
	public @ResponseBody Event save(@ModelAttribute Event model) throws IOException {

		/*
		 * To map a simple form field or URL parameter, we can annotate our method
		 * parameter with @RequestParam and set the matching name on the annotation.
		 * Example: public @ResponseBody String create( @RequestParam("comment") final
		 * String comment) {body of method} We could do this for all of our comment
		 * fields, but in this case there is a short way. Instead of the @RequestParam,
		 * we use the @ModelAttribute annotation and directly use our Comment model as
		 * the parameter type. MVC will not map each form field to a model field by
		 * name, i.e.- Input name is comment and in Comment must be a field named
		 * comment or better a setter for it; it follows the Java Beans way.
		 */

		Event event = service.put(model);
		// System.out.println("DONE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		return event;

	}

	@RequestMapping(value = "/events", method = RequestMethod.GET) // and more produces, consumes itp
	@ResponseStatus(value = HttpStatus.OK)
	public @ResponseBody Iterable<Event> getAll() throws IOException {

		return service.getAllEvents();

	}

	@RequestMapping(value = "/event/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable(value = "id") Long id, HttpServletResponse response) throws IOException {// (value = "id") opcjonalne!!!
		service.deleteEvent(id);
	}

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.OK)
	/*public @ResponseBody Event get(@ModelAttribute Long id, HttpServletResponse response)
	 * throws IOException, NotFoundException {//model musi mieć konstruktor bezparamentowy Long go 
	 * //nie ma trzeba by konwertować ze stringa a lepiej użyć 
	 */

	// public @ResponseBody Event get(@RequestAttribute("id") Long id, HttpServletResponse
	//atrybut podobnie jak zcieżka idzie w ciele adresu, 
	//chciałem zrobić geta z id w ciele zapytania ale!!
	//wysyłanie danych przez  przez get w ciele zapytania jest / zle widziane i tak nie powinno sie robić, 
	// postman prawdopodobnie nie obsługuje tego 
	//https://github.com/postmanlabs/postman-app-support/issues/131
	//https://stackoverflow.com/questions/978061/http-get-with-request-body/983458#983458
	//!!!!!! The HTTP specification says in section 4.3
	//A message-body MUST NOT be included in a request if the specification of the request method (section 5.1.1) does not allow sending an entity-body in requests.
	//The GET method means retrieve whatever information (in the form of an entity) is identified by the Request-URI.
	//public @ResponseBody Event get(@RequestBody Long id) throws IOException, NotFoundException {
	
	public @ResponseBody Event get(@RequestParam Long id) throws NotFoundException {
	
			return service.getEvent(id);
		
	}
	/*
	 * Difference between @RequestParam and @RequestAttribute
	 * 
	 * @RequestParam is used to bind parameter values from 'query string' e.g. in
	 * http://www.example.com?myParam=3, myParam=3 can populate @RequestParam
	 * parameter. On the other hand,
	 * 
	 * @RequestAttribute is to access objects which have been populated on the
	 * server-side but during the same HTTP request, for example they can be
	 * populated in an interceptor or a filter. The difference is same as of the
	 * difference between ServletRequest#getParameter(name) and
	 * ServletRequest#getAttribute(name)
	 */

	@ExceptionHandler(EmptyResultDataAccessException.class)//łapacz wyjątków pustych zapytan SQL
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public void handle404(Exception ex, Locale locale) {

	}

}
