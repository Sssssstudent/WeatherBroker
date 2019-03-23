package com.bellintegrator.dao.forecast;


import com.bellintegrator.model.Forecast;

/**
 * DAO для работы с Forecast
 */
public interface ForecastDao {

    /**
     * Сохранить прогноз погоды в базу данных
     *
     * @param forecast прогноз погоды
     */
    void save(Forecast forecast);
}
