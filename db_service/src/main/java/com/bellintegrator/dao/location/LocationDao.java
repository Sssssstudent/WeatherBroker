package com.bellintegrator.dao.location;


import com.bellintegrator.model.Location;

/**
 * DAO для работы с LocationView
 */
public interface LocationDao {

    /**
     * Сохранить местоположение в базу данных
     *
     * @param location местоположение
     */
    void save(Location location);

    /**
     * Найти местоположение по идентификатору города
     * @param woeid идентификатор города (WOEID - Where On Earth IDentifier)
     * @return местоположение
     */
    Location findByWoeid(Integer woeid);

    /**
     * Найти местоположение по названию города
     *
     * @param city город
     * @return местоположение
     */
    Location findByCity(String city);
}
