package com.dreamteam.genericdao.dao.impl;

import com.dreamteam.genericdao.dao.GenericDao;
import com.dreamteam.genericdao.dao.exception.GenericDaoException;
import com.dreamteam.genericdao.shared.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Implementing the interface Generic Dao
 *
 * @author Anna S. Almielka
 */


public class GenericDaoImpl<T> implements GenericDao<T> {

    protected Class<T> entityClass;

    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public GenericDaoImpl() {
    }

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(T t) {
        getTransactionWithPersistenceContext(em -> em.persist(t));
    }

    @Override
    public Optional<T> read(Long id) {
        return Optional.ofNullable(getReturningTransactionWithPersistenceContext(em -> em.find(entityClass, id)));
    }

    @Override
    public List<T> readAll() {
        return getReturningTransactionWithPersistenceContext(em ->
                em.createQuery("SELECT t FROM " + entityClass.getName() + " t", entityClass).getResultList());
    }

    @Override
    public void update(T t) {
        getTransactionWithPersistenceContext(em -> em.merge(t));
    }

    @Override
    public void delete(T t) {
        getTransactionWithPersistenceContext(em -> {
            T mergedT = em.merge(t);
            em.remove(mergedT);
        });
    }

    private void getTransactionWithPersistenceContext(Consumer<EntityManager> entityManagerConsumer) {
        em = FactoryManager.getEntityManager();
        try {
            em.getTransaction().begin();
            entityManagerConsumer.accept(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new GenericDaoException("Error performing dao operation. Transaction is rolled back!", e);
        } finally {
            em.close();
        }
    }

    private <T> T getReturningTransactionWithPersistenceContext(Function<EntityManager, T> entityManagerFunction) {
        em = FactoryManager.getEntityManager();
        T result;
        try {
            em.getTransaction().begin();
            result = entityManagerFunction.apply(em);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new GenericDaoException("Error performing dao operation. Transaction is rolled back!", e);
        } finally {
            em.close();
        }
        return result;
    }
}
