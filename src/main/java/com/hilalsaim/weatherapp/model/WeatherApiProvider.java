package com.hilalsaim.weatherapp.model;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class WeatherApiProvider {
    private String name;
    private String url;
    private String apiKey;
    private String jsonNode;
}
