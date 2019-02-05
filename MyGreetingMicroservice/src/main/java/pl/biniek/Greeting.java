package pl.biniek;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data
public class Greeting {
//	@Getter 
	private  long id;
//	@Getter
	private  String zawartosc;

public Greeting () {}


	public Greeting(long id, String zawartosc) {
		super();
		this.id = id;
		this.zawartosc = zawartosc;
	}



	public long getId() {
		return id;
	}



	public String getZawartosc() {
		return zawartosc;
	}


	public void setId(long id) {
		this.id = id;
	}


	public void setZawartosc(String zawartosc) {
		this.zawartosc = zawartosc;
	}
	
	

}
