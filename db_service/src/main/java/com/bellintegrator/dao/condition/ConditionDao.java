package com.bellintegrator.dao.condition;


import com.bellintegrator.model.Condition;

/**
 * DAO для работы с ConditionView
 */
public interface ConditionDao {

    /**
     * Сохранить информацию о текущем состоянии погоды в базу данных
     *
     * @param condition текущее состояние погоды
     */
    void save(Condition condition);
}
