package pl.biniek;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

 @Getter//project Lombok
@Setter
//@AllArgsConstructor
public class Vehicle implements  Serializable {

	private static final long serialVersionUID = 727530978477806712L;
	String name;
	int wheels;
	boolean hasDrivingWheel;
	boolean isTruck;
	boolean isSlowMoving;
//lombok or this	
	public Vehicle(String name, int wheels, boolean hasDrivingWheel, boolean isTruck, boolean isSlowMoving) {
		super();
		this.name = name;
		this.wheels = wheels;
		this.hasDrivingWheel = hasDrivingWheel;
		this.isTruck = isTruck;
		this.isSlowMoving = isSlowMoving;
	}
}

