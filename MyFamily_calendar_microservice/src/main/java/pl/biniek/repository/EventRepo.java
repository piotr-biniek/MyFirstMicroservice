package pl.biniek.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import pl.biniek.model.Event;

public interface EventRepo extends CrudRepository<Event, Long>{
		
		
		 //można też tak zamiast zapytania 1go tzn findByPageId(String pageId);
		  
	//	  @Query("select a from Event a where a.pageId = :pageId")
	//	  List<Event> findByPageId1(@Param("pageId") String thePageId);

		  
		//zamiast query używamy find i odpowiednia składnia nam to załatwia
		
	boolean existsByName(String name);
	

	Optional<Event> findByName(String name);
	 

	 
	 


}
