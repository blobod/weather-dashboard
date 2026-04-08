package com.weather.backend.service;

import com.weather.backend.dto.WeatherDto;
import com.weather.backend.model.SearchHistory;
import com.weather.backend.repository.SearchHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private SearchHistoryRepository searchHistoryRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    private WeatherDto mockWeatherDto;

    @BeforeEach
    void setUp() {
        // Build a fake WeatherDto that simulates OpenWeatherMap response
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
    void getWeather_shouldReturnWeatherDto_whenCityIsValid() {
        // Arrange
        when(restTemplate.getForObject(anyString(), eq(WeatherDto.class)))
                .thenReturn(mockWeatherDto);
        when(searchHistoryRepository.save(any(SearchHistory.class)))
                .thenReturn(new SearchHistory());

        // Act
        WeatherDto result = weatherService.getWeather("London");

        // Assert
        assertNotNull(result);
        assertEquals("London", result.getName());
        assertEquals(20.0, result.getMain().getTemp());
        assertEquals("GB", result.getSys().getCountry());
    }

    @Test
    void getWeather_shouldSaveToHistory_whenCityIsSearched() {
        // Arrange
        when(restTemplate.getForObject(anyString(), eq(WeatherDto.class)))
                .thenReturn(mockWeatherDto);
        when(searchHistoryRepository.save(any(SearchHistory.class)))
                .thenReturn(new SearchHistory());

        // Act
        weatherService.getWeather("London");

        // Assert and verify save was called exactly once
        verify(searchHistoryRepository, times(1)).save(any(SearchHistory.class));
    }

    @Test
    void getWeather_shouldThrowException_whenCityIsInvalid() {
        // Arrange
        when(restTemplate.getForObject(anyString(), eq(WeatherDto.class)))
                .thenThrow(new RuntimeException("City not found"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> weatherService.getWeather("InvalidCity123"));
    }

    @Test
    void getHistory_shouldReturnListOfSearchHistory() {
        // Arrange
        SearchHistory history = new SearchHistory();
        history.setCityName("London");
        history.setTemperature(20.0);

        when(searchHistoryRepository.findTop10ByOrderBySearchedAtDesc())
                .thenReturn(List.of(history));

        // Act
        List<SearchHistory> result = weatherService.getHistory();

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("London", result.get(0).getCityName());
    }

    @Test
    void getHistory_shouldReturnEmptyList_whenNoSearches() {
        // Arrange
        when(searchHistoryRepository.findTop10ByOrderBySearchedAtDesc())
                .thenReturn(List.of());

        // Act
        List<SearchHistory> result = weatherService.getHistory();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}