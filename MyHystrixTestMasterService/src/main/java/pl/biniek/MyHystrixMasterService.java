package pl.biniek;

import java.security.Principal;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import pl.biniek.bearertokenextractor.MyBearerTokenExtractor;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableResourceServer
public class MyHystrixMasterService {

	@Autowired 
	MyBearerTokenExtractor bearerTokenExtractor;
	

	@Value("${subService.MyHystrixTestMasterService.path}")
	private String anotherServiceHttp;

	public static void main(String[] args) {
		SpringApplication.run(MyHystrixMasterService.class, args);
	}

	@RequestMapping("/")
	@HystrixCommand(fallbackMethod = "fallback")
	public List<?> getVehicles(javax.servlet.http.HttpServletRequest request) {
		// TODO create some commons with vehicle

		RestTemplate restTemplate = new RestTemplate();
		String token = getToken(request);
		System.out.println(token);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	//	headers.setContentType(MediaType.APPLICATION_JSON);//wtf błędy
		headers.set("Authorization", "Bearer "+token);

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<List> result = restTemplate.exchange(anotherServiceHttp, HttpMethod.GET, entity, List.class);
		List<?> myList = result.getBody();

		return myList;

	}

	public List<?> fallback(javax.servlet.http.HttpServletRequest request) {
		List<String> list = new ArrayList<>();
		list.add("Hystrix protectes you!, subservice is down");
		return list;
	}

	String getToken(javax.servlet.http.HttpServletRequest request) {
				
		return bearerTokenExtractor.extractToken(request);
		
	}


// TODO pomyśleć o przeniesieniu tego do góry (jakiś common zrobić żeby wszędzie
// kodu nie powtarzać
//



	@Bean
	public MyBearerTokenExtractor bearerTokenExtractor() {
		return new MyBearerTokenExtractor();
	}
}

//////////////////// CALLING VARIABLES FROM FILE ///////////////////
//https://www.baeldung.com/spring-value-annotation
/*
 * application.properties custom-app.enable-mocks = false
 * 
 * @Value("${custom-app.enable-mocks}") private boolean enableMocks;
 * 
 * //OR
 * 
 * 
 * @Autowired private Environment env; ....
 * 
 * public void method() { ..... String path =
 * env.getProperty("userBucket.path"); ..... }
 * 
 * //OR
 * 
 * @ConfigurationProperties can be used to to map values from .properties( .yml
 * also supported) to a POJO.
 * 
 * Consider the following Example file.
 * 
 * .properties
 * 
 * cust.data.employee.name=Sachin cust.data.employee.dept=Cricket Employee.java
 * 
 * import org.springframework.boot.context.properties.ConfigurationProperties;
 * import org.springframework.context.annotation.Configuration;
 * 
 * @ConfigurationProperties(prefix = "cust.data.employee")
 * 
 * @Configuration("employeeProperties") public class Employee {
 * 
 * private String name; private String dept;
 * 
 * //Getters and Setters go here }
 * 
 * Now the properties value can be accessed by autowiring employeeProperties as
 * follows.
 * 
 * @Autowired private Employee employeeProperties;
 * 
 * public void method() {
 * 
 * String employeeName = employeeProperties.getName(); String employeeDept =
 * employeeProperties.getDept();
 * 
 * }
 * 
 * ciekawostki
 * https://stackoverflow.com/questions/45192373/how-to-assign-a-value-from-
 * application-properties-to-a-static-variable
 * 
 */