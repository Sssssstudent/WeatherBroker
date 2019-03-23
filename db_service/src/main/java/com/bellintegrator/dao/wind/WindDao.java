package com.bellintegrator.dao.wind;


import com.bellintegrator.model.Wind;

/**
 * DAO для работы с WindView
 */
public interface WindDao {

    /**
     * Сохранить текущую информацию о ветре в базу данных
     *
     * @param wind текущая информация о ветре
     */
    void save(Wind wind);
}
