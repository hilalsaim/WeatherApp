package com.hilalsaim.weatherapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.hilalsaim.weatherapp.config.WeatherApiConfig;
import com.hilalsaim.weatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatcherController {

    @Autowired
    WeatherService weatherService;

    @Autowired
    WeatherApiConfig weatherApiConfig;

    @GetMapping("/")
    public String mainPage(Model model) {
        model.addAttribute("weatherProviders", weatherApiConfig.getWeatherApiConfigMap());
        return "main";
    }

    @PostMapping("/search")
    public String searchWeather(@RequestParam String city, @RequestParam String weatherProviders, Model model)
            throws JsonProcessingException {
        model.addAttribute("result", weatherService.getWeatherForecast(city, weatherProviders));
        return "search";
    }
}
