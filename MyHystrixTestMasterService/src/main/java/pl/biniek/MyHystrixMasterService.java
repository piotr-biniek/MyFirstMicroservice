package pl.biniek;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
public class MyHystrixMasterService {

	
	@Value("${subService.MyHystrixTestMasterService.path}")
	private  String anotherServiceHttp;
	

	public static void main(String[] args) {
		SpringApplication.run( MyHystrixMasterService .class, args);
	}


	@RequestMapping("/")
	  @HystrixCommand(fallbackMethod = "fallback")
	public List<?> getVehicles(){
	//TODO create some commons with vehicle
		
	     
	    RestTemplate restTemplate = new RestTemplate();
	     
	    HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
	     
	    ResponseEntity<List> result = restTemplate.exchange(anotherServiceHttp, HttpMethod.GET, entity, List.class);
	  	List<?> myList=result.getBody(); 
	     
	   return myList;
	    
	    
	    
	}
	
    List<?> fallback(){
    	List<String> list = new ArrayList<>();
       list.add("Hystrix protectes you!, subservice is down");
    	return list;
    }

}


//////////////////// CALLING VARIABLES FROM FILE ///////////////////
//https://www.baeldung.com/spring-value-annotation
/*
application.properties
custom-app.enable-mocks = false

@Value("${custom-app.enable-mocks}")
private boolean enableMocks;

//OR


@Autowired
private Environment env;
....

public void method() {
    .....  
    String path = env.getProperty("userBucket.path");
    .....
}

//OR

@ConfigurationProperties can be used to to map values from .properties( .yml also supported) to a POJO.

Consider the following Example file.

.properties

cust.data.employee.name=Sachin
cust.data.employee.dept=Cricket
Employee.java

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "cust.data.employee")
@Configuration("employeeProperties")
public class Employee {

    private String name;
    private String dept;

    //Getters and Setters go here
}

Now the properties value can be accessed by autowiring employeeProperties as follows.

@Autowired
private Employee employeeProperties;

public void method() {

   String employeeName = employeeProperties.getName();
   String employeeDept = employeeProperties.getDept();

}

ciekawostki
https://stackoverflow.com/questions/45192373/how-to-assign-a-value-from-application-properties-to-a-static-variable

*/