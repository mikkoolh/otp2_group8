package com.automaatio.model.database;

import java.util.List;

import jakarta.persistence.*;

/**
 * DAO for Device Feature
 *
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class FeatureDAO implements IDAO {

    /**
     * Adds a new feature
     *
     * @param feature A new feature
     */
    public void addObject(Object feature) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            em.merge((Feature) feature);
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

    @Override
    public void deleteObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Feature feature = em.find(Feature.class, id);
            if (feature != null) {
                em.remove(feature);
                System.out.println("Feature " + id + " deleted");
            } else {
                throw new IllegalArgumentException("Feature with id  " + id + " was not found");
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
     * Fetches a feature
     *
     * @param id ID of the feature
     * @return Feature object
     */
    public Feature getObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            Feature feature = em.find(Feature.class, id);
            em.getTransaction().commit();
            return feature;
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
        System.out.println("Not in use for this class");
        return null;
    }

    /**
     * Fetches all features
     *
     * @return A list of Feature objects
     */
    public List<Feature> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<Feature> query = em.createQuery("SELECT f FROM Feature f", Feature.class);
            List<Feature> features = query.getResultList();
            return features;
        } finally {
            em.getTransaction().commit();
        }
    }

    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            String sql = "DELETE FROM Feature";
            Query query = em.createQuery(sql);
            int deletedCount = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Poistettu " + deletedCount + " ominaisuutta.");
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