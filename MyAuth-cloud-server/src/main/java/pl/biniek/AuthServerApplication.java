package pl.biniek;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}
	@RequestMapping("/user")//restowy endpoint dla sprawdzania tokena, mamy 2x resource serwer - 1 podaje tokana 2mu
	// przydał by sie pewnie jakiś tokenstore bo tu przerzucamy sie tokenem przez resta
	
	Principal getUser(Principal user) {
		return user;
	}
	
	
	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception
	{
	   oauthServer.checkTokenAccess("permitAll()");    
	}// chciałem sie dostac do auth serwera do jego endpointa sprawdzającego tokeny
	

}

