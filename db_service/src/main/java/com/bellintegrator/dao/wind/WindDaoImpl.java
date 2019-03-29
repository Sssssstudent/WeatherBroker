package com.bellintegrator.dao.wind;


import com.bellintegrator.model.Wind;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class WindDaoImpl implements WindDao {

    @PersistenceContext(unitName = "MySQLPU")
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     *
     * @param wind
     */
    @Override
    public void save(Wind wind) {
        entityManager.persist(wind);
    }
}
