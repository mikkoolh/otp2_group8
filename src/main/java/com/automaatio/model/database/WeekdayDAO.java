package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.Query;
import java.util.List;

/**
 * @author Mikko Hänninen
 * @author Elmo Erla
 * 11.09.2023
 *
 * DAO for Weekday.class
 */
public class WeekdayDAO implements IDAO {

      @Override
    public void addObject(Object object) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist((Weekday) object);
        em.getTransaction().commit();
    }

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
     * Fetches all weekdays
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
     * Updates an existing weekday
     * @param weekday The weekday to update
     */
    public void updateWeekday(Weekday weekday) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.merge(weekday);
        em.getTransaction().commit();
    }

    /**
     * Deletes a weekday by its ID
     * @param id ID of the weekday to delete
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