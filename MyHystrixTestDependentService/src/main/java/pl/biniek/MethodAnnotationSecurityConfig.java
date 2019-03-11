package pl.biniek;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.oauth2.provider.expression.OAuth2MethodSecurityExpressionHandler;



/**
 * method enabling Annotation authorisation check in oauth2 based on 
 * stackoverflow.com/questions/33638850/how-to-protect-spring-security-oauth-resources-using-preauthorize-based-on-scop
 * @author piotr.biniek
 *
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MethodAnnotationSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new OAuth2MethodSecurityExpressionHandler();
    }
}
