package com.weather.backend.service;

import com.weather.backend.dto.WeatherDto;
import com.weather.backend.model.SearchHistory;
import com.weather.backend.repository.SearchHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final SearchHistoryRepository searchHistoryRepository;
    private final RestTemplate restTemplate;

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.base.url}")
    private String baseUrl;

    public WeatherDto getWeather(String city) {
        String url = baseUrl + "/weather?q=" + city + "&appid=" + apiKey + "&units=metric";
        WeatherDto weatherDto = restTemplate.getForObject(url, WeatherDto.class);
        saveToHistory(weatherDto, city);
        return weatherDto;
    }

    public List<SearchHistory> getHistory() {
        return searchHistoryRepository.findTop10ByOrderBySearchedAtDesc();
    }

    private void saveToHistory(WeatherDto dto, String city) {
        SearchHistory history = new SearchHistory();
        history.setCityName(city);
        history.setTemperature(dto.getMain().getTemp());
        history.setWeatherDescription(dto.getWeather().get(0).getDescription());
        history.setCountry(dto.getSys().getCountry());
        searchHistoryRepository.save(history);
    }
}