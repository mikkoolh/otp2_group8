package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * @author Matleena Kankaanpää
 * 26.9.2023
 *
 * DAO for Routine class
 */

public class RoutineDAO implements IDAO {

    @Override
    public void addObject(Object object) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            em.persist((Routine) object);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public Routine addAndReturnObject(Object object) {
        Routine savedRoutine;

        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            savedRoutine = em.merge((Routine) object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return savedRoutine;
    }


    @Override
    public void deleteObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Routine routine = em.find(Routine.class, id);
            if (routine != null) {
                em.remove(routine);
                System.out.println("routine " + id + " deleted");
            } else {
                throw new IllegalArgumentException("routine with id  " + id + " was not found");
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
        try {
            Routine routine = em.find(Routine.class, id);
            em.getTransaction().commit();
            return routine;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }

    }

    @Override
    public Object getObject(String s) {
        System.out.println("Not in use for this class");
        return null;
    }

    /**
     * Fetches all routines
     *
     * @return A list of Routine objects
     */
    public List<Routine> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            TypedQuery<Routine> query = em.createQuery("SELECT r FROM Routine r", Routine.class);
            List<Routine> routines = query.getResultList();
            em.getTransaction().commit();
            return routines;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception for the caller to handle
        } finally {
            em.close();
        }
    }

    /**
     * Fetches routines by device ID
     *
     * @param deviceId The ID of the device
     * @return A list of Routine objects for the specified device
     */
    public List<Routine> getRoutinesByDeviceId(int deviceId) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            TypedQuery<Routine> query = em.createQuery("SELECT r FROM Routine r WHERE r.deviceID.id = :deviceId", Routine.class)
                    .setParameter("deviceId", deviceId);
            List<Routine> routines = query.getResultList();
            em.getTransaction().commit();
            return routines;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * @param id        ID of the routine
     * @param automated Boolean indicating whether the routine is automatized or not
     */
    public void toggleOnOff(int id, boolean automated) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            Routine routine = em.find(Routine.class, id);
            routine.setAutomated(!automated);
            System.out.println("updated routine");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void setAutomated(int id, boolean setTo) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            Routine routine = em.find(Routine.class, id);
            routine.setAutomated(setTo);
            System.out.println("updated routine");
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Updates the time of a routine
     * @param routineId
     * @param newTime
     * @param automated
     */
    public void updateTime(int routineId, EventTime newTime, boolean automated) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            Routine routine = em.find(Routine.class, routineId);

            if (routine != null) {
                routine.setEventTime(newTime);
                routine.setAutomated(automated);
                System.out.println("updated routine");
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
     * Delete all routines for a device by a certain user
     *
     * @param device
     * @param user
     */
    public void deleteByDeviceAndUser(Device device, User user) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            Query query = em.createQuery("DELETE FROM Routine WHERE deviceID = :device AND user = :username");
            query.setParameter("device", device);
            query.setParameter("username", user);
            int deletedCount = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Poistettu " + deletedCount + " rutiinia.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
