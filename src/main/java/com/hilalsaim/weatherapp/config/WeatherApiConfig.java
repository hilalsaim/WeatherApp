package com.hilalsaim.weatherapp.config;

import com.hilalsaim.weatherapp.model.WeatherApiProvider;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "weather")
@Getter
public class WeatherApiConfig {
    private Map<String, WeatherApiProvider> weatherApiConfigMap = new HashMap<>();
}
