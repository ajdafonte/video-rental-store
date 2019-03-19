package com.casumo.hometest.videorentalstore.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * WebConfig class.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer
{
    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters)
    {
        for (final HttpMessageConverter<?> converter : converters)
        {
            if (converter instanceof MappingJackson2HttpMessageConverter)
            {
                final MappingJackson2HttpMessageConverter jsonMessageConverter = (MappingJackson2HttpMessageConverter) converter;
                final ObjectMapper objectMapper = jsonMessageConverter.getObjectMapper();
                // Disable the date format (WRITE_DATES_AS_TIMESTAMPS) which leads to ISO conform yyyy-MM-dd'T'HH:mm:ss.SSSZ
                objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
                break;
            }
        }
    }
}
