package pl.biniek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class MyGreetingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyGreetingServiceApplication.class, args);
	}

}  

