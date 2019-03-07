package pl.biniek;

/**
 * based on: https://thepracticaldeveloper.com/2017/06/27/hystrix-fallback-with-zuul-and-spring-boot/
 * + small modifications resulting from @Deprecated ZuulFallbackProvider
 * work only for registered routes (autoconfigurated from eureka are not working)
 * 
 */


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import com.netflix.hystrix.exception.HystrixTimeoutException;



@Configuration
public class HystrixFallbackConfiguration {
 
    @Bean
    public FallbackProvider fallbackProvider() {
        return new FallbackProvider() {
			
			@Override
			public String getRoute() {
                // it's the serviceId property and not the route!!!!!!
                return "*";
            }
			
			
			@Override
			public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
                return new ClientHttpResponse() {

                  @Override
                  public InputStream getBody() throws IOException {
                      return new ByteArrayInputStream("{\"Object\":\"Sorry, it lookd that service  is down!\"}".getBytes());
                  }

                  @Override
                  public HttpHeaders getHeaders() {
                      HttpHeaders headers = new HttpHeaders();
                      headers.setContentType(MediaType.APPLICATION_JSON);
                      return headers;
                  }

					 @Override
                   public HttpStatus getStatusCode() throws IOException {
                       return HttpStatus.OK;
                   }

                   @Override
                   public int getRawStatusCode() throws IOException {
                       return HttpStatus.OK.value();
                   }

                 @Override
                 public String getStatusText() throws IOException {
                     return HttpStatus.OK.toString();
                 }

					@Override
					public void close() {

					}
                 };
             }
   };
    }
}

//class HystrixFallbackConfiguration implements FallbackProvider {
//@Override
//public String getRoute() {
//    return "hello";
//}
//
//@Override
//public ClientHttpResponse fallbackResponse(String route, final Throwable cause) {
//    if (cause instanceof HystrixTimeoutException) {
//        return response(HttpStatus.GATEWAY_TIMEOUT);
//    } else {
//        return response(HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//}
//
//private ClientHttpResponse response(final HttpStatus status) {
//    return new ClientHttpResponse() {
//        @Override
//        public HttpStatus getStatusCode() throws IOException {
//            return status;
//        }
//
//        @Override
//        public int getRawStatusCode() throws IOException {
//            return status.value();
//        }
//
//        @Override
//        public String getStatusText() throws IOException {
//            return status.getReasonPhrase();
//        }
//
//        @Override
//        public void close() {
//        }
//
//        @Override
//        public InputStream getBody() throws IOException {
//            return new ByteArrayInputStream("fallback".getBytes());
//        }
//
//        @Override
//        public HttpHeaders getHeaders() {
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            return headers;
//        }
//    };
//}
//}