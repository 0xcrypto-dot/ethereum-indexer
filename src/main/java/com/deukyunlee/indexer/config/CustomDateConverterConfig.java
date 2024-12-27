package com.deukyunlee.indexer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

/**
 * Created by dukedev1004@crossangle.io on 2024. 12. 22.
 */
@Configuration
public class CustomDateConverterConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Instant>() {
            @Override
            public Instant convert(String source) {
                LocalDate localDate = LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyyMMdd"));
                return localDate.atStartOfDay(ZoneOffset.UTC).toInstant();
            }
        });
    }
}
