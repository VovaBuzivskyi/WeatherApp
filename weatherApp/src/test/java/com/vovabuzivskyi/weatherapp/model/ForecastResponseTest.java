package com.vovabuzivskyi.weatherapp.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ForecastResponseTest {

    ForecastResponse.Main main;
    ForecastResponse.Weather weather;
    ForecastResponse.Wind wind;
    ForecastResponse.City city;
    ForecastResponse.WeatherData weatherData;
    List<ForecastResponse.Weather> weatherList;
    List<ForecastResponse.WeatherData> weatherDataList;
    ForecastResponse forecastResponse;

    @BeforeEach
    public void setUp() {
        main = new ForecastResponse.Main();
        main.setTemp(27.15);
        main.setPressure(90);
        main.setHumidity(70);

        weather = new ForecastResponse.Weather();
        weather.setId(10);
        weather.setDescription("Clear Sky");

        wind = new ForecastResponse.Wind();
        wind.setSpeed(12);

        city = new ForecastResponse.City();
        city.setId(1);
        city.setName("Kharkiv");
        city.setCountry("UA");

        weatherData = new ForecastResponse.WeatherData();
        weatherData.setMain(main);
        weatherList = new ArrayList<>();
        weatherList.add(weather);
        weatherData.setWeather(weatherList);
        weatherData.setWind(wind);
        weatherData.setDt_txt("This is date test");

        forecastResponse = new ForecastResponse();
        forecastResponse.setCity(city);
        weatherDataList = new ArrayList<>();
        weatherDataList.add(weatherData);
        forecastResponse.setList(weatherDataList);
    }

    @Test
    public void testMainGetTemp() {
        assertEquals(Math.round(27.15), main.getTemp());
    }

    @Test
    public void testMainSetTemp() {
        main.setTemp(32.78);
        assertEquals(Math.round(32.78), main.getTemp());
    }

    @Test
    public void testMainGetPressure() {
        assertEquals(90, main.getPressure());
    }

    @Test
    public void testMainSetPressure() {
        main.setPressure(58);
        assertEquals(58, main.getPressure());
    }

    @Test
    public void testMainGetHumidity() {
        assertEquals(70, main.getHumidity());
    }

    @Test
    public void testMainSetHumidity() {
        main.setHumidity(45);
        assertEquals(45, main.getHumidity());
    }

    @Test
    public void testWeatherGetId() {
        assertEquals(10, weather.getId());
    }

    @Test
    public void testWeatherSetId() {
        weather.setId(5);
        assertEquals(5, weather.getId());
    }

    @Test
    public void testWeatherGetDescription() {
        assertEquals("Clear Sky", weather.getDescription());
    }

    @Test
    public void testWeatherSetDescription() {
        weather.setDescription("Broken Clouds");
        assertEquals("Broken Clouds", weather.getDescription());
    }

    @Test
    public void testWindGetWind() {
        assertEquals(12, wind.getSpeed());
    }

    @Test
    public void testWindSetWind() {
        wind.setSpeed(4);
        assertEquals(4, wind.getSpeed());
    }

    @Test
    public void testCityGetId() {
        assertEquals(1, city.getId());
    }

    @Test
    public void testCitySetId() {
        city.setId(5);
        assertEquals(5, city.getId());
    }

    @Test
    public void testCityGetName() {
        assertEquals("Kharkiv", city.getName());
    }

    @Test
    public void testCitySetName() {
        city.setName("Kiev");
        assertEquals("Kiev", city.getName());
    }

    @Test
    public void testCityGetCountry() {
        assertEquals("UA", city.getCountry());
    }

    @Test
    public void testCitySetCountry() {
        city.setCountry("PL");
        assertEquals("PL", city.getCountry());
    }

    @Test
    public void testWeatherDataGetMain() {
        assertEquals(main, weatherData.getMain());
    }

    @Test
    public void testWeatherDataSetMain() {
        ForecastResponse.Main mainInMethodSet = new ForecastResponse.Main();
        mainInMethodSet.setTemp(33.46);
        mainInMethodSet.setPressure(81);
        mainInMethodSet.setHumidity(55);
        weatherData.setMain(mainInMethodSet);

        assertEquals(mainInMethodSet, weatherData.getMain());
    }

    @Test
    public void testWeatherDataGetList() {
        assertArrayEquals(weatherList.toArray(), weatherData.getWeather().toArray());
    }

    @Test
    public void testWeatherDataSetList() {
        List<ForecastResponse.Weather> listWeatherDataInMethod = new ArrayList<>();
        ForecastResponse.Weather weatherInMethodSetList = new ForecastResponse.Weather();
        weatherInMethodSetList.setId(16);
        weatherInMethodSetList.setDescription("Cloudy");
        listWeatherDataInMethod.add(weatherInMethodSetList);

        weatherData.setWeather(listWeatherDataInMethod);

        assertArrayEquals(listWeatherDataInMethod.toArray(), weatherData.getWeather().toArray());
    }

    @Test
    public void testWeatherDataGetWind() {
        assertEquals(wind, weatherData.getWind());
    }

    @Test
    public void testWeatherDataSetWind() {
        ForecastResponse.Wind windInMethodSet = new ForecastResponse.Wind();
        windInMethodSet.setSpeed(7);
        weatherData.setWind(windInMethodSet);

        assertEquals(windInMethodSet, weatherData.getWind());
    }

    @Test
    public void testWeatherDataGetDtTxt() {
        assertEquals("This is date test", weatherData.getDt_txt());
    }

    @Test
    public void testWeatherDataSetDtTxt() {
        weatherData.setDt_txt("This is date test 2");
        assertEquals("This is date test 2", weatherData.getDt_txt());
    }


    @Test
    void testGetList() {
        assertArrayEquals(weatherDataList.toArray(), forecastResponse.getList().toArray());
    }

    @Test
    void testSetList() {
//        ForecastResponse forecastResponseInMethodSetList = new ForecastResponse();
        ForecastResponse.WeatherData weatherDataInMethodSetList = new ForecastResponse.WeatherData();
        List<ForecastResponse.WeatherData> weatherDataListInMethodSetList = new ArrayList<>();

        ForecastResponse.Main mainInMethodSetList = new ForecastResponse.Main();
        mainInMethodSetList.setTemp(11);
        mainInMethodSetList.setPressure(43);
        mainInMethodSetList.setHumidity(91);

        ForecastResponse.Weather weatherInMethodSetList = new ForecastResponse.Weather();
        weatherInMethodSetList.setId(32);
        weatherInMethodSetList.setDescription("Partly Cloudy");

        List<ForecastResponse.Weather> listWeatherDataInMethodSetList = new ArrayList<>();
        listWeatherDataInMethodSetList.add(weatherInMethodSetList);

        ForecastResponse.Wind windInMethodSetList = new ForecastResponse.Wind();
        windInMethodSetList.setSpeed(14);

        weatherDataInMethodSetList.setMain(mainInMethodSetList);
        weatherDataInMethodSetList.setWeather(listWeatherDataInMethodSetList);
        weatherDataInMethodSetList.setWind(windInMethodSetList);
        weatherDataInMethodSetList.setDt_txt("Test from testSetList");

        weatherDataListInMethodSetList.add(weatherDataInMethodSetList);

        forecastResponse.setList(weatherDataListInMethodSetList);

        assertArrayEquals(weatherDataListInMethodSetList.toArray(), forecastResponse.getList().toArray());
    }

    @Test
    void testGetCity() {
        assertEquals(city, forecastResponse.getCity());
    }

    @Test
    void testSetCity() {
        ForecastResponse.City cityInMethodSetCity = new ForecastResponse.City();
        cityInMethodSetCity.setId(11);
        cityInMethodSetCity.setName("Dalat");
        cityInMethodSetCity.setCountry("VN");

        forecastResponse.setCity(cityInMethodSetCity);

        assertEquals(cityInMethodSetCity, forecastResponse.getCity());
    }
}
