package pl.biniek;

public class ErrorGenerator {

	public static String invokeTest() {
		String text= "Hello Visitor";
		if(Math.random()>0.3) throw new IllegalStateException();
		
		return text;
	}
	
}
