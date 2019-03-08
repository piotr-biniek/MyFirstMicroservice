package pl.biniek.vecicle;

import java.io.Serializable;



// @Getter//project Lombok
//@Setter
////@AllArgsConstructor
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
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWheels() {
		return wheels;
	}
	public void setWheels(int wheels) {
		this.wheels = wheels;
	}
	public boolean isHasDrivingWheel() {
		return hasDrivingWheel;
	}
	public void setHasDrivingWheel(boolean hasDrivingWheel) {
		this.hasDrivingWheel = hasDrivingWheel;
	}
	public boolean isTruck() {
		return isTruck;
	}
	public void setTruck(boolean isTruck) {
		this.isTruck = isTruck;
	}
	public boolean isSlowMoving() {
		return isSlowMoving;
	}
	public void setSlowMoving(boolean isSlowMoving) {
		this.isSlowMoving = isSlowMoving;
	}
}

