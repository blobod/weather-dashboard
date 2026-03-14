package com.weather.backend.controller;

import com.weather.backend.dto.WeatherDto;
import com.weather.backend.model.SearchHistory;
import com.weather.backend.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current")
    public ResponseEntity<WeatherDto> getCurrentWeather(@RequestParam String city) {
        WeatherDto weather = weatherService.getWeather(city);
        return ResponseEntity.ok(weather);
    }

    @GetMapping("/history")
    public ResponseEntity<List<SearchHistory>> getHistory() {
        return ResponseEntity.ok(weatherService.getHistory());
    }
}