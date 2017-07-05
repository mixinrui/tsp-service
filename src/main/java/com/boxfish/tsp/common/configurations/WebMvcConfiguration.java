package com.boxfish.tsp.common.configurations;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    private MappingJackson2HttpMessageConverter jacksonMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(this.objectMapper());
        return converter;
    }

    private ObjectMapper objectMapper() {
        return new ObjectMapper()
                .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
                .setTimeZone(TimeZone.getTimeZone("GMT+8"))
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        for (HttpMessageConverter<?> mc : converters) {
            if (mc instanceof MappingJackson2HttpMessageConverter) {
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
                objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                ((MappingJackson2HttpMessageConverter) mc).setObjectMapper(objectMapper);
            }
        }
    }

    @Bean
    public SimpleClientHttpRequestFactory simpleClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(10000);       //10秒连接超时
        factory.setReadTimeout(5000);          //5秒读数据超时

        return factory;
    }

    @Bean
    public RestTemplate restTemplate() {

        return new RestTemplate(this.simpleClientHttpRequestFactory());
    }
}
