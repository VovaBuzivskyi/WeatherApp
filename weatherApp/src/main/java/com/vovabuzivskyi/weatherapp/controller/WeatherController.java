package com.vovabuzivskyi.weatherapp.controller;

import com.vovabuzivskyi.weatherapp.model.ForecastResponse;
import com.vovabuzivskyi.weatherapp.model.WeatherResponse;
import com.vovabuzivskyi.weatherapp.service.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@Controller()
public class WeatherController {

    private static final Logger log = LoggerFactory.getLogger(WeatherController.class);

    WeatherService weatherService;

    WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/get_weather")
    public String getWeather() {
        return "get_weather";
    }

    @GetMapping("/show_weather")
    public String showWeather(@RequestParam(name = "city") String city, Model model) {
        log.debug("Method shoWeather(Controller) started for city: {} ",city);
        WeatherResponse weatherData = weatherService.getWeather(city);
        if (weatherData != null) {
            model.addAttribute("city", weatherData.getName());
            model.addAttribute("country", weatherData.getSys().getCountry());
            model.addAttribute("weatherDescription", weatherData.getWeather().get(0).getDescription());
            model.addAttribute("temperature", weatherData.getMain().getTemp());
            model.addAttribute("humidity", weatherData.getMain().getHumidity());
            model.addAttribute("windSpeed", weatherData.getWind().getSpeed());
            log.debug("Method showWeather(Controller) send data to View show_weather city: {} ",city);
            return "show_weather";
        } else
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found");
    }

    @GetMapping("/get_forecast")
    public String getForecast() {
        return "get_forecast";
    }

    @GetMapping("/show_forecast")
    public String showForecast(@RequestParam(name = "city") String city, Model model) {
        log.debug("Method showForecast(Controller) started for city: {} ",city);
        ForecastResponse forecastData = weatherService.getForecast(city);
        if (forecastData != null) {
            model.addAttribute("cityData", forecastData.getCity());
            model.addAttribute("datalist", forecastData.getList());
            Map<String, List<ForecastResponse.WeatherData>> groupedData = forecastData.getList().stream()
                    .collect(Collectors.groupingBy(data -> data.getDt_txt().substring(0, 10))); // Group by date (yyyy-MM-dd)
            // Sort the map by date
            Map<String, List<ForecastResponse.WeatherData>> sortedGroupedData = new TreeMap<>(groupedData);
            model.addAttribute("groupedData", sortedGroupedData);
            log.debug("Method showForecast(Controller) send data to View show_forecast city: {}",city);
            return "show_forecast";
        } else
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleHttpClientErrorException(HttpClientErrorException e, Model model) {
        model.addAttribute("errorMessage", "City with such name doesn't exists, check the spelling");
        log.debug("City wasn't found");
        return "error_page";
    }
}


