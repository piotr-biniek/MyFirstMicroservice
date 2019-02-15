package pl.biniek.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import pl.biniek.model.Event;
@Component
public class DataLoadingClass implements CommandLineRunner {
	
	
	@Autowired
	EventRepo repo;
	
	private DataLoadingClass(EventRepo repo) {
		this.repo = repo;
	}



	@Override
	public void run(String... args) throws Exception {
		Event ev1 = new Event();
		Event ev2 = new Event();
		ev1.setName("ev 1");
		ev2.setName("ev 2");
				
		this.repo.save(ev2);
		this.repo.save(ev1);


	}

}
