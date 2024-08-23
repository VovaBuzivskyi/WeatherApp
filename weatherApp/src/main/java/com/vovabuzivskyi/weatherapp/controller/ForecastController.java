package com.vovabuzivskyi.weatherapp.controller;

import com.vovabuzivskyi.weatherapp.model.ForecastResponse;
import com.vovabuzivskyi.weatherapp.service.ForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Controller
public class ForecastController {

    private static final Logger log = LoggerFactory.getLogger(ForecastController.class);

    ForecastService forecastService;

    ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping("/get_forecast")
    public String getForecast() {
        log.debug("Method getForecast(Controller) started");
        return "get_forecast";
    }

    @GetMapping("/show_forecast")
    public String showForecast(@RequestParam(name = "city") String city, Model model) {
        log.debug("Method showForecast(Controller) started for city: {} ",city);
        ForecastResponse forecastData = forecastService.getForecast(city);
        if (forecastData != null) {
            model.addAttribute("cityData", forecastData.getCity());
            model.addAttribute("dataListOfAllDaysForecast", forecastData.getList());
            // Group by date (yyyy-MM-dd)
            Map<String, List<ForecastResponse.WeatherData>> groupedData = forecastData.getList().stream()
                    .collect(Collectors.groupingBy(data -> data.getDt_txt().substring(0, 10)));
            // Sort the map by date
            Map<String, List<ForecastResponse.WeatherData>> sortedGroupedData = new TreeMap<>(groupedData);
            model.addAttribute("groupedDataByDateAndTime", sortedGroupedData);
            log.debug("Method showForecast(Controller) send data to View show_forecast city: {}",city);
            return "show_forecast";
        } else
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "City not found");
    }


}
