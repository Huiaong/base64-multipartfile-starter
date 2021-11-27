package com.huiaong.base64.multipartfile.starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huiaong.base64.multipartfile.starter.multipart.Base64MultipartFile;
import com.huiaong.base64.multipartfile.starter.multipart.Base64MultipartFileSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(JacksonAutoConfiguration.class)
public class JacksonConfiguration implements WebMvcConfigurer {

    @Bean
    @ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.deserializerByType(Base64MultipartFile.class, Base64MultipartFileSerializer.instance);
        };
    }

}
