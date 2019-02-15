package pl.biniek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient 
@EnableResourceServer
public class MyHelloServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyHelloServiceApplication.class, args);
	}

	@RequestMapping("/")
	String home() {
		return "Hello World!";
	}
}


