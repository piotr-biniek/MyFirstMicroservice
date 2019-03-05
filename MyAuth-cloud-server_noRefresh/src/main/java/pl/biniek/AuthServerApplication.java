package pl.biniek;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
// based on priya-k-
@SpringBootApplication
@EnableDiscoveryClient 
@EnableAuthorizationServer
@EnableResourceServer
@RestController
public class AuthServerApplication {
	
	@Resource(name="tokenStore")
	TokenStore tokenStore;

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}
	
	 
	@RequestMapping("/user")//restowy endpoint dla sprawdzania tokena, mamy 2x resource serwer - 1 podaje tokana 2mu
	// przydał by sie pewnie jakiś tokenstore bo tu przerzucamy sie tokenem przez resta
	
	Principal getUser(Principal user) {
		return user;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/tokens")
	@ResponseBody
	public List<String> getTokens() {
	    List<String> tokenValues = new ArrayList<>();
	    Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("123"); 
	    if (tokens!=null){
	        for (OAuth2AccessToken token:tokens){
	            tokenValues.add(token.getValue());
	        }
	    }
	    return tokenValues;
//	}

}
}
