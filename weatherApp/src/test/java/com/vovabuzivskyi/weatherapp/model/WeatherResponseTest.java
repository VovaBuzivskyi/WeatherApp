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

    @Test
    void testSysGetCountry(){
       assertEquals("UA",sys.getCountry());
    }

    @Test
    void testSysSetCountry(){
        sys.setCountry("Any Country");

        assertEquals("Any Country",sys.getCountry());
    }

    @Test
    void testWeatherGetId (){
        assertEquals(1,weather.getId());
    }

    @Test
    void testWeatherSetId (){
        weather.setId(3);

        assertEquals(3,weather.getId());
    }

    @Test
    void testWeatherGetDescription (){
        assertEquals("Description",weather.getDescription());
    }

    @Test
    void testWeatherSetDescription (){
        weather.setDescription("Another Description");
        assertEquals("Another Description",weather.getDescription());
    }

    @Test
    void testMainGetTemp(){
        assertEquals(Math.round(303.15-273.15),main.getTemp());
    }

    @Test
    void testMainSetTemp(){
        main.setTemp(298.15);

        assertEquals(Math.round(298.15-273.15),main.getTemp());
    }

    @Test
    void testMainGetHumidity(){
        assertEquals(52,main.getHumidity());
    }

    @Test
    void testMainSetHumidity(){
        main.setHumidity(61);

        assertEquals(61,main.getHumidity());
    }

    @Test
    void testWindGetSpeed(){
        assertEquals(1,wind.getSpeed());
    }

    @Test
    void testWindSetSpeed(){
        wind.setSpeed(4.3);

        assertEquals(4.3,wind.getSpeed());
    }



    @BeforeEach
    void setUp() {
        weatherResponse = new WeatherResponse();
        weatherResponse.setName("City");

        sys = new WeatherResponse.Sys();
        sys.setCountry("UA");
        weatherResponse.setSys(sys);

        weather = new WeatherResponse.Weather();
        weather.setId(1);
        weather.setDescription("Description");
        List<WeatherResponse.Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        weatherResponse.setWeather(weatherList);

        main = new WeatherResponse.Main();
        main.setTemp(303.15);
        main.setHumidity(52);
        weatherResponse.setMain(main);

        wind = new WeatherResponse.Wind();
        wind.setSpeed(1);
        weatherResponse.setWind(wind);
    }

    @Test
    void getName() {
        assertEquals("City", weatherResponse.getName());
    }

    @Test
    void setName() {
        weatherResponse.setName("BigCity");

        assertEquals("BigCity", weatherResponse.getName());
    }

    @Test
    void getSys() {
        assertEquals("UA", weatherResponse.getSys().getCountry());
    }

    @Test
    void setSys() {
        WeatherResponse.Sys sys = new WeatherResponse.Sys();
        sys.setCountry("Country");
        weatherResponse.setSys(sys);

        assertEquals(sys, weatherResponse.getSys());
    }

    @Test
    void getWeather() {
         WeatherResponse.Weather weatherInMethod = new WeatherResponse.Weather();
         weatherInMethod.setId(1);
         weatherInMethod.setDescription("Description");
         List<WeatherResponse.Weather> weatherList = new ArrayList<>();
         weatherList.add(weatherInMethod);

         assertArrayEquals(weatherList.toArray(), weatherResponse.getWeather().toArray());
    }

    @Test
    void setWeather() {
        WeatherResponse.Weather weather = new WeatherResponse.Weather();
        weather.setId(2);
        weather.setDescription("Second Description");
        List<WeatherResponse.Weather> weatherList = new ArrayList<>();
        weatherList.add(weather);
        weatherResponse.setWeather(weatherList);

        assertArrayEquals(weatherList.toArray(), weatherResponse.getWeather().toArray());
    }

    @Test
    void getMain() {
        WeatherResponse.Main mainInMethod = new WeatherResponse.Main();
        mainInMethod.setTemp(303.15);
        mainInMethod.setHumidity(52);

        assertEquals(mainInMethod, weatherResponse.getMain());
    }

    @Test
    void setMain() {
        WeatherResponse.Main mainInMethod2= new WeatherResponse.Main();
        mainInMethod2.setTemp(21.50);
        mainInMethod2.setHumidity(56);
        weatherResponse.setMain(mainInMethod2);

        assertEquals(mainInMethod2, weatherResponse.getMain());
    }

    @Test
    void getWind() {
        assertEquals(1,weatherResponse.getWind().getSpeed());
    }

    @Test
    void setWind() {
        WeatherResponse.Wind windInMethod = new WeatherResponse.Wind();
        windInMethod.setSpeed(5);
        weatherResponse.setWind(windInMethod);

        assertEquals(5,weatherResponse.getWind().getSpeed());
    }
}