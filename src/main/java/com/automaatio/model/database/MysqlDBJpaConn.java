package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * The MysqlDBJpaConn class is used to create and retrieve an EntityManager instance
 * for managing the lifecycle of JPA entities.
 *
 * @author Mikko Hänninen, Elmo Erla, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0
 */
public class MysqlDBJpaConn {

    private static EntityManagerFactory emf = null;
    private static EntityManager em = null;

    private MysqlDBJpaConn() {
        // Private constructor to prevent instantiation
    }

    /**
     * Returns a singleton instance of the EntityManager. If the instance
     * hasn't been created yet, it will be created.
     *
     * @return An EntityManager object
     */
    public static EntityManager getInstance() {
        if (em == null || !em.isOpen()) {
            if (emf == null) {
                emf = Persistence.createEntityManagerFactory("DevPU");
            }
            em = emf.createEntityManager();
        }
        return em;
    }

    /**
     * Closes the EntityManager instance.
     */
    public static void close() {
        if (em != null && em.isOpen()) {
            em.close();
        }

        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
