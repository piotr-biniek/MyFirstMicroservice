package pl.biniek;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableResourceServer
public class MyHystrixDependentService {

	public static void main(String[] args) {
		SpringApplication.run(MyHystrixDependentService.class, args);
	}

	@RequestMapping(value = "/getmydata", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public List<Vehicle> invokeTest() {

/**
 * Generating Error sometimes :)
 */
		if (Math.random() > 0.66)
			throw new IllegalStateException();

		return VehicleGenerator.getVehicles();
	}

}
/////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////SOME example  DATA!!////////////////////////////////
///// some test  classes for calling rest +rest + Hystrix  ///////////////////////////////////////////////
@Getter//project Lombok
@Setter
@AllArgsConstructor
class Vehicle implements  Serializable {

	private static final long serialVersionUID = 727530978477806712L;
	String name;
	int wheels;
	boolean hasDrivingWheel;
	boolean isTruck;
	boolean isSlowMoving;
	
//	public Vehicle(String name, int wheels, boolean hasDrivingWheel, boolean isTruck, boolean isSlowMoving) {
//		super();
//		this.name = name;
//		this.wheels = wheels;
//		this.hasDrivingWheel = hasDrivingWheel;
//		this.isTruck = isTruck;
//		this.isSlowMoving = isSlowMoving;
//	}
}

///////////////// Vechicle generator
class VehicleGenerator {

	public static List<Vehicle> getVehicles() {

		List<Vehicle> list = new ArrayList<Vehicle>();
		for (int i = 0; i < 10; i++) {
			list.add(new Vehicle("Vehicle" + i, i % 5+2, VehicleGenerator.generateDataofVehicle(),
					VehicleGenerator.generateDataofVehicle(), VehicleGenerator.generateDataofVehicle()));

		}
		return list;
	}

// test Vehicle data Geneator	
	private static boolean generateDataofVehicle() {
		if ((Math.random() * 10) % 2 == 1)
			return true;
		return false;

	}

}
