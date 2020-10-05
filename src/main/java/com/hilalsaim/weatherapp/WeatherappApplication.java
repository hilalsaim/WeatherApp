package com.hilalsaim.weatherapp;

import com.hilalsaim.weatherapp.config.WeatherApiConfig;
import com.hilalsaim.weatherapp.service.WeatherService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(WeatherApiConfig.class)
public class WeatherappApplication implements CommandLineRunner {

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherApiConfig weatherApiConfig;

    public static void main(String[] args) {
        SpringApplication.run(WeatherappApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        String t = weatherService.getWeatherForecast("Krakow", "openweathermap");
        log.info("Temp: {}", t);

        weatherApiConfig.getWeatherApiConfigMap().forEach(
                (x,y) -> {log.info("key:{} value:{}", x, y);}
        );
    }
}
