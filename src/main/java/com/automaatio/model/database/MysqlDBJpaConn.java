package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class MysqlDBJpaConn {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    private MysqlDBJpaConn() {
        // Private constructor to prevent instantiation
    }

    public static EntityManager getInstance() {
        if (em == null || !em.isOpen()) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("DevPU");
            }
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }

        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
