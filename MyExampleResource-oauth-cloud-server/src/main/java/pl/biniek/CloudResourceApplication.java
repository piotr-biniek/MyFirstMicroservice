package pl.biniek;
//based on priya-k-
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableResourceServer
@EnableDiscoveryClient 
@RestController
public class CloudResourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudResourceApplication.class, args);
	}
	/////////Baza!!!! :) z danymi do pozyskania
	
	
	Map<Integer, Account> accMap = new HashMap<>();
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	private Collection<Account> getAccountDetails() {
		if(ObjectUtils.isEmpty(accMap)) {
		
		accMap.put(111111, new Account(111111, "USD", "Biniek","fajowy"));
		accMap.put(111112, new Account(111112, "PLN", "Biniek","fajowy"));
		accMap.put(111113, new Account(111113, "EUR", "Biniek","fajowy"));
		}
		
		return accMap.values();
	
	}

}

