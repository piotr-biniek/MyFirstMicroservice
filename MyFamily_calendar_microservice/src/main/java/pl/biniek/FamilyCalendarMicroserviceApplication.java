package pl.biniek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@EnableTransactionManagement
//@EntityScan
//@ComponentScan
@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableResourceServer
public class FamilyCalendarMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FamilyCalendarMicroserviceApplication.class, args);
	}  
	
//	@RequestMapping("/piotr")
//	String home() {
//		return "Hello World!";
//	}

}

