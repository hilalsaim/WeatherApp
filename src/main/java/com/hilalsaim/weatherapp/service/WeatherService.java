package com.hilalsaim.weatherapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hilalsaim.weatherapp.config.WeatherApiConfig;
import com.hilalsaim.weatherapp.model.WeatherApiProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class WeatherService {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    WeatherApiConfig weatherApiConfig;

    public String getWeatherForecast(String city, String provider) throws JsonProcessingException {
        WeatherApiProvider weatherApiProvider = weatherApiConfig.getWeatherApiConfigMap().get(provider);


        if(weatherApiProvider.getName().equals("AccuWeather")) {
            String urls;

            StringBuilder strURL = new StringBuilder();
            strURL.append("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=xfUMShyqdAzApKoOZdfRYWo9BguEUszf&q=");
            strURL.append(city);

            urls = strURL.toString();

            ResponseEntity<String> responseCity
                    = restTemplate.getForEntity(urls, String.class);
            JsonNode pNode = new ObjectMapper().readTree(responseCity.getBody());
            city=pNode.get(0).get("Key").toString();
        }
        String url = weatherApiProvider.getUrl()
                .replace("{city}", city)
                .replace("{apikey}", weatherApiProvider.getApiKey());

        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);

        return getTempature(response.getBody(), weatherApiProvider.getJsonNode());
    }

    public String getTempature(String responseJson, String jsonNode) throws JsonProcessingException {

        JsonNode productNode = new ObjectMapper().readTree(responseJson);

        for(String node : jsonNode.split("\\.")) {
            Pattern pattern = Pattern.compile("\\[([0-9]+)\\]");
            Matcher matcher = pattern.matcher(node);
            if(matcher.find()) {
                productNode = productNode.get(Integer.valueOf(matcher.group(1)));
            } else {
                productNode = productNode.get(node);
            }
        }

        return productNode.toString();
    }
}
