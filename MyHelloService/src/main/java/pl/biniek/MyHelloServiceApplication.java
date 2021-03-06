package pl.biniek;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.security.Principal;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedAuthoritiesExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.discovery.converters.Auto;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
@EnableResourceServer
public class MyHelloServiceApplication {

	@Autowired
	AuthoritiesExtractor authExtractor;

	public static void main(String[] args) {
		SpringApplication.run(MyHelloServiceApplication.class, args);
	}

	@RequestMapping("/")
	String home() {
		return "Hello World! ";
	}

	@RequestMapping("/auth")
	List<GrantedAuthority> auth(@Autowired Principal principal) {

		return authExtractor.extractAuthorities(getUserAuthenticationDetails(principal));// +authoritiesExtractor.//principalExtractor.extractPrincipal(map);
	}

	@RequestMapping("/userdata")
	public Map<String, Object> user(@Autowired Principal principal) {
		return getUserAuthenticationDetails(principal);
	}

	// TODO pomyśleć o przeniesieniu tego do góry (jakiś common zrobić żeby wszędzie
	// kodu nie powtarzać
	//

	public Map<String, Object> getUserAuthenticationDetails(Principal principal) {
		if (principal != null) {
			OAuth2Authentication oAuth2Authentication = (OAuth2Authentication) principal;
			Authentication authentication = oAuth2Authentication.getUserAuthentication();
			Map<String, Object> userDetails = new LinkedHashMap<>();
			/**
			 * TODO uwaga, na 2 pozycji dostajemy różne obiekty w cały świat 2h nad tym
			 * dumałem ... początkowy <string,string> (z Stackoverflow) powodował wyjątki
			 * przy odpakowaniu tu jako object czasem jast też mapa czasem lista a czasem
			 * string
			 */
			userDetails = (Map<String, Object>) authentication.getDetails();

			return userDetails;
		}
		return null;
	}

//
//

	@Bean
	public AuthoritiesExtractor fixedAuthoritiesExtractor() {
		return new FixedAuthoritiesExtractor();
	}
}

//    DO ŚMIECI BO MAMY FIXED AUTH EXTRACTOR
//     class MyAuthoritiesExtractor  implements AuthoritiesExtractor {
//    	 
//    	 @Override
//    	    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
//    		 Map<String, GrantedAuthority> authorities = new HashMap<>();
//    		if(map.get("authorities")!=null) {
//    		//	 authorities=(Map<String , GrantedAuthority>) map.get("authorities");
//    		 //		return  
//    			List<List> lista =(List<List>) map.get("authorities");
//    			Map<String, GrantedAuthority> mapa2 =(Map<String, GrantedAuthority>) lista.get(0);
//    		 		Set<String> set =  mapa2.keySet();
//    		 		for (String string : set) {
//    		 			System.out.println(string);
//						
//					}
//    		}
//    	  //     return authorities;
//    return null;  }	 
//}

/*
 * 
 * details = (Map<String, String>) authentication.getDetails(); Set<String> det=
 * details.keySet(); System.out.println(det); Iterator itr = det.iterator();
 * Map<String, String> map = new LinkedHashMap<>(); while(itr.hasNext()){ String
 * key = (String)itr.next(); System.out.println(key); map.put(key, key); //
 * ArrayList<String> = details.get(key); // //
 * System.out.println(details.get("authorities"));
 * 
 * // } // logger.info("details = " + details); // id, email, name, link etc. }
 * return map; } return null; }
 */
//	    
//	
//	
//	@Autowired
//	PrincipalExtractor principalExtractor;
//@Autowired
//	AuthoritiesExtractor authoritiesExtractor;
// Authentication authentication;

// OAuth2AuthenticationDetails oAuth2AuthenticationDetails;

// SecurityContextHolder.getContext().getAuthentication();
//	String token = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();

//inaczej to trzeba	
//	@RequestMapping("/user")
//	public String read(@Autowired OAuth2Authentication auth) {
//	  return auth.getOAuth2Request().getClientId();
//	}
//	

//}

//
//@Bean
//public Principal principal() {
//  return new MyPrincipal();
//}
//
//class MyPrincipal  implements Principal {
//
//	@Override
//	public String getName() {
//		// TODO Auto-generated method stub
//		return null;
//	}}

//	@RequestMapping("/auth")
//	List<GrantedAuthority> getAuth() {
//		 
//		return (List<GrantedAuthority> )authExtraktor.extractAuthorities(getUserAuthenticationDetails(principal));	
//		}
