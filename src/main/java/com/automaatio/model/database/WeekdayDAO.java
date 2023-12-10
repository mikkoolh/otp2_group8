package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;
import java.util.List;

/**
 * The WeekdayDAO class represents a DAO (Data Access Object)
 * for carrying out database operations related to the Weekday entity.
 *
 * @author Mikko Hänninen, Elmo Erla
 * @version 1.0 11.09.2023
 */

public class WeekdayDAO implements IDAO {

    /**
     * Adds a new weekday to the database.
     *
     * @param object A new weekday to be added
     */
    @Override
    public void addObject(Object object) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist((Weekday) object);
        em.getTransaction().commit();
    }

    /**
     * Deletes a weekday from the database.
     *
     * @param id    The ID of the weekday to be deleted
     */
    @Override
    public void deleteObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Weekday weekday = em.find(Weekday.class, id);
            if (weekday != null) {
                em.remove(weekday);
                System.out.println("Weekday " + id + " deleted");
            } else {
                throw new IllegalArgumentException("Weekday with id  " + id + " was not found");
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
     * Fetches a weekday by ID.
     *
     * @param id    The ID of the weekday to be fetched
     * @return      Weekday with the specified ID or null if not found
     */
    @Override
    public Object getObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        Weekday weekday = em.find(Weekday.class, id);
        em.getTransaction().commit();
        return weekday;
    }

    @Override
    public Object getObject(String s) {
        System.out.println("Not in use for this class.");
        return null;
    }

    /**
     * Fetches all weekdays.
     *
     * @return A list of Weekday objects
     */
    public List<Weekday> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<Weekday> query = em.createQuery("SELECT w FROM Weekday w", Weekday.class);
            List<Weekday> weekdays = query.getResultList();
            return weekdays;
        } finally {
            em.getTransaction().commit();
        }
    }


    /**
     * Deletes a weekday from the database.
     *
     * @param id    ID of the weekday to delete
     */
    public void deleteWeekday(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        Weekday weekday = em.find(Weekday.class, id);
        if (weekday != null) {
            em.remove(weekday);

        }
        em.getTransaction().commit();
    }

    /**
     * Deletes all weekdays from the database.
     */
    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        String sql = "DELETE FROM Weekday";
        Query query = em.createQuery(sql);

        int deletedCount = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println("Poistettu " + deletedCount + " viikonpäivää.");
    }
}