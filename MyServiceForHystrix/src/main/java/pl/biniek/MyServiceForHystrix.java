package pl.biniek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker

public class MyServiceForHystrix {


	public static void main(String[] args) {
		SpringApplication.run(MyServiceForHystrix.class, args);
	}


	@RequestMapping("/")
	  @HystrixCommand(fallbackMethod = "fallback")
	String home() {
		return ErrorGenerator.invokeTest();
	}
	
    String fallback(){
        return "Hystrix protectes you!";
    }

}
