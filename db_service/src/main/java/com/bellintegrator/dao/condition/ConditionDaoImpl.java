package com.bellintegrator.dao.condition;


import com.bellintegrator.model.Condition;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class ConditionDaoImpl implements ConditionDao {

    @PersistenceContext(unitName = "MySQLPU")
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     *
     * @param condition
     */
    @Override
    public void save(Condition condition) {
        entityManager.persist(condition);
    }
}
