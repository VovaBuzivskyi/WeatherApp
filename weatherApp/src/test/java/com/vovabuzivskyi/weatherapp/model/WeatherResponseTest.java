package com.vovabuzivskyi.weatherapp.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherResponseTest {

    WeatherResponse weatherResponse;
    WeatherResponse.Weather weather;
    WeatherResponse.Main main;
    WeatherResponse.Wind wind;
    WeatherResponse.Sys sys;
    List<WeatherResponse.Weather> weatherList;

    @BeforeEach
    void setUp() {
        sys = new WeatherResponse.Sys();
        sys.setCountry("UA");

        weather = new WeatherResponse.Weather();
        weather.setId(1);
        weather.setDescription("Description");

        weatherList= new ArrayList<>();
        weatherList.add(weather);

        main = new WeatherResponse.Main();
        main.setTemp(303.15);
        main.setHumidity(52);

        wind = new WeatherResponse.Wind();
        wind.setSpeed(1);

        weatherResponse = new WeatherResponse();
        weatherResponse.setName("City");
        weatherResponse.setSys(sys);
        weatherResponse.setWeather(weatherList);
        weatherResponse.setMain(main);
        weatherResponse.setWind(wind);
    }

    @Test
    void TestGetName() {
        assertEquals("City", weatherResponse.getName());
    }

    @Test
    void testSetName() {
        weatherResponse.setName("BigCity");

        assertEquals("BigCity", weatherResponse.getName());
    }

    @Test
    void testSysGetCountry() {
        assertEquals("UA", sys.getCountry());
    }

    @Test
    void testSysSetCountry() {
        sys.setCountry("Any Country");

        assertEquals("Any Country", sys.getCountry());
    }

    @Test
    void testWeatherGetId() {
        assertEquals(1, weather.getId());
    }

    @Test
    void testWeatherSetId() {
        weather.setId(3);

        assertEquals(3, weather.getId());
    }

    @Test
    void testWeatherGetDescription() {
        assertEquals("Description", weather.getDescription());
    }

    @Test
    void testWeatherSetDescription() {
        weather.setDescription("Another Description");

        assertEquals("Another Description", weather.getDescription());
    }

    @Test
    void testMainGetTemp() {
        assertEquals(Math.round(303.15 - 273.15), main.getTemp());
    }

    @Test
    void testMainSetTemp() {
        main.setTemp(298.15);

        assertEquals(Math.round(298.15 - 273.15), main.getTemp());
    }

    @Test
    void testMainGetHumidity() {
        assertEquals(52, main.getHumidity());
    }

    @Test
    void testMainSetHumidity() {
        main.setHumidity(61);

        assertEquals(61, main.getHumidity());
    }

    @Test
    void testWindGetSpeed() {
        assertEquals(1, wind.getSpeed());
    }

    @Test
    void testWindSetSpeed() {
        wind.setSpeed(4.3);

        assertEquals(4.3, wind.getSpeed());
    }

    @Test
    void testGetSys() {
        assertEquals(sys, weatherResponse.getSys());
    }

    @Test
    void testSetSys() {
        WeatherResponse.Sys sysInMethodSetSys = new WeatherResponse.Sys();
        sys.setCountry("Country");

        weatherResponse.setSys(sysInMethodSetSys);

        assertEquals(sysInMethodSetSys, weatherResponse.getSys());
    }

    @Test
    void testGetWeather() {
        assertArrayEquals(weatherList.toArray(), weatherResponse.getWeather().toArray());
    }

    @Test
    void testSetWeather() {
        WeatherResponse.Weather weather = new WeatherResponse.Weather();
        weather.setId(2);
        weather.setDescription("Description");
        List<WeatherResponse.Weather> weatherListInMethodSetWeather = new ArrayList<>();
        weatherListInMethodSetWeather.add(weather);

        weatherResponse.setWeather(weatherListInMethodSetWeather);

        assertArrayEquals(weatherListInMethodSetWeather.toArray(), weatherResponse.getWeather().toArray());
    }

    @Test
    void testGetMain() {
        assertEquals(main, weatherResponse.getMain());
    }

    @Test
    void testSetMain() {
        WeatherResponse.Main mainInMethod2SetMain = new WeatherResponse.Main();
        mainInMethod2SetMain.setTemp(21.50);
        mainInMethod2SetMain.setHumidity(56);
        weatherResponse.setMain(mainInMethod2SetMain);

        assertEquals(mainInMethod2SetMain, weatherResponse.getMain());
    }

    @Test
    void testGetWind() {
        assertEquals(wind, weatherResponse.getWind());
    }

    @Test
    void testSetWind() {
        WeatherResponse.Wind windInMethodSetWind = new WeatherResponse.Wind();
        windInMethodSetWind.setSpeed(5);

        weatherResponse.setWind(windInMethodSetWind);

        assertEquals(windInMethodSetWind, weatherResponse.getWind());
    }

}