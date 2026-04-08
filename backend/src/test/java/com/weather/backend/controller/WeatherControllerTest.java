package com.weather.backend.controller;

import com.weather.backend.dto.WeatherDto;
import com.weather.backend.model.SearchHistory;
import com.weather.backend.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    private WeatherDto mockWeatherDto;

    @BeforeEach
    void setUp() {
        mockWeatherDto = new WeatherDto();

        WeatherDto.MainDto main = new WeatherDto.MainDto();
        main.setTemp(20.0);
        main.setHumidity(60.0);
        main.setFeelsLike(18.0);
        mockWeatherDto.setMain(main);

        WeatherDto.WindDto wind = new WeatherDto.WindDto();
        wind.setSpeed(5.0);
        mockWeatherDto.setWind(wind);

        WeatherDto.WeatherDescDto desc = new WeatherDto.WeatherDescDto();
        desc.setDescription("clear sky");
        desc.setIcon("01d");
        mockWeatherDto.setWeather(List.of(desc));

        WeatherDto.SysDto sys = new WeatherDto.SysDto();
        sys.setCountry("GB");
        mockWeatherDto.setSys(sys);

        mockWeatherDto.setName("London");
    }

    @Test
    void getCurrentWeather_shouldReturn200_whenCityIsValid() throws Exception {
        when(weatherService.getWeather("London")).thenReturn(mockWeatherDto);

        mockMvc.perform(get("/api/weather/current")
                        .param("city", "London"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("London"))
                .andExpect(jsonPath("$.main.temp").value(20.0))
                .andExpect(jsonPath("$.sys.country").value("GB"));
    }

    @Test
    void getCurrentWeather_shouldReturn500_whenServiceThrowsException() throws Exception {
        when(weatherService.getWeather("InvalidCity123"))
                .thenThrow(new RuntimeException("City not found"));

        mockMvc.perform(get("/api/weather/current")
                        .param("city", "InvalidCity123"))
                .andExpect(status().isInternalServerError());
    }

    @Test
    void getCurrentWeather_shouldReturn400_whenCityParamIsMissing() throws Exception {
        mockMvc.perform(get("/api/weather/current"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getHistory_shouldReturn200_withListOfSearchHistory() throws Exception {
        SearchHistory history = new SearchHistory();
        history.setId(1L);
        history.setCityName("London");
        history.setTemperature(20.0);
        history.setCountry("GB");
        history.setWeatherDescription("clear sky");
        history.setSearchedAt(LocalDateTime.now());

        when(weatherService.getHistory()).thenReturn(List.of(history));

        mockMvc.perform(get("/api/weather/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].cityName").value("London"))
                .andExpect(jsonPath("$[0].temperature").value(20.0))
                .andExpect(jsonPath("$[0].country").value("GB"));
    }

    @Test
    void getHistory_shouldReturn200_withEmptyList() throws Exception {
        when(weatherService.getHistory()).thenReturn(List.of());

        mockMvc.perform(get("/api/weather/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }
}