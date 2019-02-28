package pl.biniek;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/*
 * zamiast cekcji konfiguracyjnej
 * spring.security.user.name=allen
 * spring.security.user.password=pass
 * spring.security.user.roles=USER
 */

//based on priya-k-
@Configuration
public class SecurityKonfig extends GlobalAuthenticationConfigurerAdapter {

	public void init(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("allen").password("{noop}pass").roles("USER")
				// dodano noop bo dla spring >=5 trzeba albo podać w
				// hasle noopa żeby nie rzucał wujątkiem
				// inMemoryAuthentication().withUser("user").password("{noop}password"))
				// albo withDefaultPasswordEncoder() - też deprecated
				.and()
				.withUser("ben").password("{noop}pass").roles("USER", "ADMIN");

		/*
		 * albo PasswordEncoder encoder =
		 * PasswordEncoderFactories.createDelegatingPasswordEncoder(); UserDetails user
		 * = User.withUsername("someusername")
		 * .password(encoder.encode("somepassword")).roles("USER").build();
		 */

	}

	public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
		oauthServer.checkTokenAccess("permitAll()");
	}

}
// class OAuthSecurityConfig extends AuthorizationServerConfigurerAdapter{
//	   @Override 
//	   public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception { 
//	       oauthServer.checkTokenAccess("permitAll()").tokenKeyAccess("permitAll()"); 
//	   }
//	}