package com.bellintegrator.dao.astronomy;


import com.bellintegrator.model.Astronomy;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class AstronomyDaoImpl implements AstronomyDao {

    @PersistenceContext(unitName = "MySQLPU")
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     *
     * @param astronomy
     */
    @Override
    public void save(Astronomy astronomy) {
        entityManager.persist(astronomy);
    }
}
