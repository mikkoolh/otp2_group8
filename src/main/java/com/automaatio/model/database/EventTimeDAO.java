package com.automaatio.model.database;
import java.util.List;

import jakarta.persistence.*;

/**
 * DAO for EventTime
 * @author Matleena Kankaanpää
 * 28.9.2023
 */

public class EventTimeDAO implements IDAO {

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

    @Override
    public Object getObject(String s) {
        System.out.println("Method not in use in this class");
        return null;
    }

    /**
     * Fetches all events
     * @return A list of EventTime objects
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