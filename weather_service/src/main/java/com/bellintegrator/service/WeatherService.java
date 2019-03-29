package com.bellintegrator.service;


import com.bellintegrator.dto.yahooforecast.YahooForecast;

/**
 * Копия интерфейса, удаленно вызываемого через Hessian
 */
public interface WeatherService {

    /**
     * Получить данные о погоде из БД
     *
     * @param city название города
     * @return данные о погоде
     */
    YahooForecast getWeatherFromDB(String city);
}
