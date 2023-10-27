package com.automaatio.model.database;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * @author Nikita Nossenko
 *
 * DAO for UserType.
 */

public class UserTypeDAO implements IDAO {

    /**
     * Adds a new User Type
     * @param userType A new User Type
     */
    public void addObject(Object userType) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist((UserType) userType);
        em.getTransaction().commit();
    }

    @Override
    public void deleteObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            UserType userType = em.find(UserType.class, id);
            if (userType != null) {
                em.remove(userType);
                System.out.println("UserType" + id + " deleted");
            } else {
                throw new IllegalArgumentException("UserType with id  " + id + " was not found");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }

    }

    /**
     * Fetches a User Type by its ID
     * @param id ID of UserType
     * @return UserType object
     */
    public UserType getObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        UserType userType = em.find(UserType.class, id);
        em.getTransaction().commit();
        return userType;
    }

    @Override
    public Object getObject(String s) {
        System.out.println("Not in use for this class");
        return null;
    }

    /**
     * Fetches all User Types
     * @return A list of UserType objects
     */
    public List<UserType> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<UserType> query = em.createQuery("SELECT u FROM UserType u", UserType.class);
            List<UserType> userTypes = query.getResultList();
            return userTypes;
        } finally {
            em.getTransaction().commit();
        }
    }

    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        String sql = "DELETE FROM UserType ";
        Query query = em.createQuery(sql);

        int deletedCount = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println("Poistettu " + deletedCount + " käyttäjätyyppiä.");
    }
}