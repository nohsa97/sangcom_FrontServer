package capstone.sangcom.configuration.api;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    private ResponseErrorHandler responseErrorHandler;

    public RestTemplateConfig(ResponseErrorHandler responseErrorHandler){
        this.responseErrorHandler = responseErrorHandler;
    }

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplateBuilder()
                .requestFactory(() -> new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()))
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .errorHandler(responseErrorHandler)
                .additionalMessageConverters(new StringHttpMessageConverter(Charset.forName("UTF-8")))
                .additionalMessageConverters(new MappingJackson2HttpMessageConverter())
                .build();

    }
}
