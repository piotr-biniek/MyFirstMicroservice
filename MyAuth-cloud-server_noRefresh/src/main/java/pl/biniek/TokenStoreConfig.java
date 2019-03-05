package pl.biniek;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class TokenStoreConfig {
	
	
	
	@Bean
	public TokenStore tokenStore() {
		return new JdbcTokenStore(dataSource());
	}
	
// nie wiem czemu nie chce utworzyc domyslej bazy na plkach app... prop sypie wyjątkami
//@Bean
//https://docs.spring.io/spring-boot/docs/current/reference/html/howto-data-access.html#howto-configure-a-datasource
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().build();
//	}
	@Bean
	 @Primary
	public DataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName("org.h2.Driver");
		//source.setUrl("jdbc:h2:~/springtutorial;INIT=runscript from 'classpath:/create.sql'");
		source.setUrl("jdbc:h2:tcp://localhost/~/test7");
		source.setUsername("sa");
		return source;
	}
	
	
	
	
	/**
	 * The class that handles all tokens is the DefaultTokenServices – and has to be defined as a bean in our configuration:
	 * @return
	 */
	@Bean
	@Primary
	public DefaultTokenServices tokenServices() {
	    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
	    defaultTokenServices.setTokenStore(tokenStore());
	    defaultTokenServices.setSupportRefreshToken(true);
	    return defaultTokenServices;
	}
	
	
//STARE wersje	
//@ConfigurationProperties(prefix="app.datasource")// lub @ConfigurationProperties("app.datasource") 

//	@Value("${spring.datasource.url}")
//	private String datasourceUrl;
//
//
//	@Value("${spring.datasource.username}")
//	private String dbUsername;
//
//	@Value("${spring.datasource.password}")
//	private String dbPassword;
//
//	@Value("${spring.datasource.driver-class-name}")
//	private String dbDriverClassName;
	
//	DriverManagerDataSource 
//	Simple implementation of the standard JDBC DataSource interface, configuring the plain old JDBC DriverManager via bean properties, 
//	and returning a new Connection from every getConnection call.
//	NOTE: This class is not an actual connection pool; it does not actually pool Connections.
//	It just serves as simple replacement for a full-blown connection pool, implementing the same standard interface, 
//	but creating new Connections on every call.
//
//	Useful for test or standalone environments outside of a Java EE container, either as a DataSource bean in a corresponding ApplicationContext 
//	or in conjunction with a simple JNDI environment. Pool-assuming Connection.close() calls will simply close the Connection, 
//	so any DataSource-aware persistence code should work.
//	
//	@Bean
//	public DataSource dataSource() {
//		DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(dbDriverClassName);
//		dataSource.setUrl(datasourceUrl);
//		dataSource.setUsername(dbUsername);
//		dataSource.setPassword(dbPassword);
//		return dataSource;
//	}
	

	
	
	
	}




////////////////////////////////inaczej ///////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////	
//# ==============================================================
//		# = Data Source
//		# ==============================================================
//		spring.datasource.url = jdbc:mysql://localhost:3306/login?useSSL=true
//		spring.datasource.username = root
//		spring.datasource.password = admin
//
//@Repository("userRepo")
//public interface UserRepo extends JpaRepository<User, Long> {
//    User findByEmail(String email);
//}

////////////////////////////////////////////////
////////////////DtaSource Bean
//@Bean
//public DataSource dataSource() {
//	return DataSourceBuilder.create().url("jdbc:mysql://192.168.99.100:33306/default?useSSL=false")
//			.username("default").password("default").driverClassName("com.mysql.jdbc.Driver").build();
//}


		
		
		////////////////////////////////////////////////////
		/////////////////////////////////// inny sposób
//		
//		@Autowired
//		private JdbcTemplate jdbcTemplate;
//		Properties:
//
//		spring.datasource.url=jdbc:postgresql://my_url:my_port/my_other_stuff
//		spring.datasource.username=my_user_name
//		spring.datasource.password=my_password
//		spring.datasource.driver-class-name=org.postgresql.Driver
//		This create a DataSource of class: org.apache.tomcat.jdbc.pool.DataSource

