package com.bellintegrator.dao.atmosphere;


import com.bellintegrator.model.Atmosphere;

/**
 * DAO для работы с AtmosphereView
 */
public interface AtmosphereDao {

    /**
     * Сохранить информацию о текущем атмосферном давлении, влажности и видимости в базу данных
     *
     * @param atmosphere информация о текущем атмосферном давлении, влажности и видимости
     */
    void save(Atmosphere atmosphere);
}
