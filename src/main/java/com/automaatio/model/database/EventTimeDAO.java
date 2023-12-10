package com.automaatio.model.database;
import java.util.List;

import jakarta.persistence.*;

/**
 * The EventTimeDAO class represents a DAO (Data Access Object)
 * for carrying out database operations related to the EventTime entity.
 *
 * @author Matleena Kankaanpää
 * @version 1.0 28.9.2023
 */

public class EventTimeDAO implements IDAO {

    /**
     * Adds a new event time object to the database
     *
     * @param object A new event time to be added
     */
    @Override
    public void addObject(Object object) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            em.merge((EventTime) object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Adds a new event time object to the database and returns it.
     *
     * @param object    A new event time object
     * @return          The new event time object that was added to the database
     */
    public EventTime addAndReturnObject(Object object) {
        EventTime savedEventTime;

        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            savedEventTime = em.merge((EventTime) object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return savedEventTime;
    }

    /**
     * Deletes an event time from the database
     *
     * @param id    The id of the event time to be deleted
     */
    @Override
    public void deleteObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            EventTime eventTime = em.find(EventTime.class, id);
            if (eventTime != null) {
                em.remove(eventTime);
                System.out.println("EventTime " + id + " deleted");
            } else {
                throw new IllegalArgumentException("EventTime with id  " + id + " was not found");
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
     * Fetches an event time object from the database by ID
     *
     * @param id    The id of the event time object to be fetched
     * @return      An event time object with the specified ID, or null if not found
     */
    @Override
    public Object getObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            EventTime eventTime = em.find(EventTime.class, id);
            em.getTransaction().commit();
            return eventTime;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * This method is not used in this class.
     */
    @Override
    public Object getObject(String s) {
        System.out.println("Method not in use in this class");
        return null;
    }

    /**
     * Fetches all event times from the database
     *
     * @return      A list of EventTime objects
     */
    public List<EventTime> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<EventTime> query = em.createQuery("SELECT e FROM EventTime e", EventTime.class);
            List<EventTime> eventTimes = query.getResultList();
            return eventTimes;
        } finally {
            em.getTransaction().commit();
        }
    }

    /**
     * Deletes all event times from the database
     */
    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            String sql = "DELETE FROM EventTime";
            Query query = em.createQuery(sql);
            int deletedCount = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Poistettu " + deletedCount + " tapahtuma-aikaa.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}