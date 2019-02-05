package pl.biniek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerServiceEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerServiceEurekaApplication.class, args);
	}

}

 