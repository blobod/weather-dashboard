package com.weather.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {

    private String name;
    private MainDto main;
    private WindDto wind;
    private List<WeatherDescDto> weather;
    private SysDto sys;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MainDto {
        private Double temp;
        private Double humidity;
        @JsonProperty("feels_like")
        private Double feelsLike;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WindDto {
        private Double speed;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WeatherDescDto {
        private String description;
        private String icon;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SysDto {
        private String country;
    }
}