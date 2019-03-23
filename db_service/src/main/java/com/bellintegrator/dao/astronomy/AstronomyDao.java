package com.bellintegrator.dao.astronomy;


import com.bellintegrator.model.Astronomy;

/**
 * dao для работы с AstronomyView
 */
public interface AstronomyDao {

    /**
     * Сохранить информацию о текущих астрономических условиях в базу данных
     *
     * @param astronomy информация о текущих астрономических условиях
     */
    void save(Astronomy astronomy);
}
