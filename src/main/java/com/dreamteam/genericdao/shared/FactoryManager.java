package com.dreamteam.genericdao.shared;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author Anna S. Almielka
 */

public class FactoryManager {

    private static EntityManagerFactory emf;

    private static EntityManager em;

    private static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory("genericdao");
        return emf;
    }

    public static EntityManager getEntityManager() {
        // if (em == null)
        em = getEntityManagerFactory().createEntityManager();
        return em;
    }
}
