package pl.biniek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
//@Enableoa

public class MyZuulFrontServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyZuulFrontServerApplication.class, args);
	}
  
}

