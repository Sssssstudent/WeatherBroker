package com.bellintegrator.dao.atmosphere;


import com.bellintegrator.model.Atmosphere;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * {@inheritDoc}
 */
@RequestScoped
public class AtmosphereDaoImpl implements AtmosphereDao {

    @PersistenceContext(unitName = "MySQLPU")
    private EntityManager entityManager;

    /**
     * {@inheritDoc}
     * @param atmosphere
     */
    @Override
    public void save(Atmosphere atmosphere) {
        entityManager.persist(atmosphere);
    }
}
