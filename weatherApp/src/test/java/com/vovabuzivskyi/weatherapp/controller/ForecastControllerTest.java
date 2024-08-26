package com.vovabuzivskyi.weatherapp.controller;

import com.vovabuzivskyi.weatherapp.model.ForecastResponse;
import com.vovabuzivskyi.weatherapp.service.ForecastService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ForecastController.class)
class ForecastControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ForecastService forecastService;

    @InjectMocks
    ForecastController forecastController;

    @Test
    void getForecastReturnOkStatus() throws Exception {
        mockMvc.perform(get("/get_forecast"))
                .andExpect(status().isOk())
                .andExpect(view().name("get_forecast"))
                .andReturn();
    }

    @Test
    void showForecastWithWalidCity() throws Exception {
        String city = "San Francisco";
        ForecastResponse.City cityData = new ForecastResponse.City();
        cityData.setName(city);

        ForecastResponse.Weather weather = new ForecastResponse.Weather();
        weather.setId(10);
        weather.setDescription("Some description");
        List<ForecastResponse.Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);

        ForecastResponse.Main main = new ForecastResponse.Main();
        main.setTemp(32.45);
        main.setHumidity(48);

        ForecastResponse.Wind wind = new ForecastResponse.Wind();
        wind.setSpeed(4.78);

        ForecastResponse.WeatherData weatherData1 = new ForecastResponse.WeatherData();
        weatherData1.setDt_txt("2024-08-25 12:00:00");
        weatherData1.setWeather(weatherList);
        weatherData1.setMain(main);
        weatherData1.setWind(wind);


        ForecastResponse.WeatherData weatherData2 = new ForecastResponse.WeatherData();
        weatherData2.setDt_txt("2024-08-25 15:00:00");
        weatherData2.setWeather(weatherList);
        weatherData2.setMain(main);
        weatherData2.setWind(wind);

        ForecastResponse.WeatherData weatherData3 = new ForecastResponse.WeatherData();
        weatherData3.setDt_txt("2024-08-26 12:00:00");
        weatherData3.setWeather(weatherList);
        weatherData3.setMain(main);
        weatherData3.setWind(wind);

        List<ForecastResponse.WeatherData> weatherDataList = Arrays.asList(weatherData1, weatherData2, weatherData3);
        ForecastResponse forecastResponse = new ForecastResponse();
        forecastResponse.setCity(cityData);
        forecastResponse.setList(weatherDataList);

        when(forecastService.getForecast(city)).thenReturn(forecastResponse);

        mockMvc.perform(get("/show_forecast").param("city", city))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("cityData"))
                .andExpect(model().attributeExists("dataListOfAllDaysForecast"))
                .andExpect(model().attributeExists("groupedDataByDateAndTime"))
                .andExpect(view().name("show_forecast"));
    }

    @Test
    void showForecastWithInvalidCity() throws Exception {
        String city = "InvalidCity";
        when(forecastService.getForecast(anyString())).thenReturn(null);

        mockMvc.perform(get("/show_forecast").param("city", city))
                .andExpect(status().isOk())
                .andExpect(view().name("error_page"))
                .andExpect(model().attributeExists("errorMessage"))
                .andExpect(model().attribute("errorMessage", "City with such name doesn't exist, check the spelling"));
    }
}