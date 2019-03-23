package com.bellintegrator.service;


import dto.yahooforecast.YahooForecast;

/**
 * Сервис для обработки данных о погоде из БД
 */
public interface WeatherService {

    /**
     * Получить данные о погоде из БД
     *
     * @param city название города
     * @return данные о погоде
     */
    YahooForecast getWeatherFromDB(String city);

    /**
     * Сохранить данные о погоде в БД
     *
     * @param yahooForecast данные о погоде
     */
    void saveWeather(YahooForecast yahooForecast);
}
