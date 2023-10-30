package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Locale;

/**
 * @author Mikko Hänninen
 * @author Nikita Nossenko
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserDAO implements IDAO {

    /**
     * Adds a new user
     * @param user A new user
     */
    public void addObject(Object user) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist((User) user);
        em.getTransaction().commit();
    }

    @Override
    public void deleteObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
                System.out.println("User " + id + " deleted");
            } else {
                throw new IllegalArgumentException("User with id  " + id + " was not found");
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
            User user = em.find(User.class, id);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches a user by username
     * @param username Username
     * @return User object
     */
    public User getObject(String username) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            User user = getUserQuery(em, username).getSingleResult();
            em.getTransaction().commit();
            return user;
        } catch (NoResultException e) {
            em.getTransaction().rollback();
            return null;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void updatePassword(String username, String oldPassword, String newPassword) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            User user = getUserQuery(em, username).getSingleResult();
            if (user != null) {
                if (BCrypt.checkpw(oldPassword, user.getPassword())) {
                    String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                    user.setPassword(newHashedPassword);
                    em.merge(user);
                    em.getTransaction().commit();
                } else {
                    System.out.println("Väärä salasana");
                }
            } else {
                System.out.println("Käyttäjää ei löytynyt");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches all users
     * @return A list of users
     */
    public List<User> getAll() {
        try (EntityManager em = MysqlDBJpaConn.getInstance()) {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            List<User> users = query.getResultList();
            em.getTransaction().commit();
            return users;
        }
    }

    /**
     * Deletes all users
     */
    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        String sql = "DELETE FROM User ";
        Query query = em.createQuery(sql);

        int deletedCount = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println("Poistettu " + deletedCount + " käyttäjää.");
    }

    public void updateMaxPrice(double price, String username){
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            User user = getUserQuery(em, username).getSingleResult();
            user.setMaxPrice(price);
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Käyttäjää ei löytynyt");
            throw e;
        } finally {
            em.close();
        }
    }

    public double getMaxPrice(String username) {
        double maxPrice = 0;
        EntityManager em = MysqlDBJpaConn.getInstance();
        try (em) {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            if (user != null) {
                maxPrice = user.getMaxPrice();
                em.getTransaction().commit();
            } else {
                maxPrice = 0.0;
            }
        } catch (Exception e) {
            System.out.println("Ongelma tietojen hakemisessa.");
        }
        return maxPrice;
    }

    public void updatePicture(String username, int selectedPictureId) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            User user = getUserQuery(em, username).getSingleResult();

            if (user != null) {
                user.setSelectedPicture(selectedPictureId);
                em.merge(user);
                em.getTransaction().commit();
                System.out.println("Picture updated for user: " + username);
            } else {
                System.out.println("User not found: " + username);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error updating picture for user: " + username);
            throw e;
        } finally {
            em.close();
        }
    }

    public void updateLocale(String username, Locale locale){
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            User user = em.find(User.class, username);

            if (user != null) {
                user.setLocale(locale.toString());
                em.merge(user);
                em.getTransaction().commit();
                System.out.println("Locale updated to: "+locale.toString()+" for user: " + username);
            } else {
                System.out.println("User not found: " + username);
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error updating Locale for user: " + username);
            throw e;
        } finally {
            em.close();
        }
    }
    public Locale getLocale(String username){
        Locale locale = new Locale("en", "US");
        EntityManager em = MysqlDBJpaConn.getInstance();
        try (em) {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            if (user != null) {
                locale = user.getLocale();
                em.getTransaction().commit();
            } else {

            }
        } catch (Exception e) {
            System.out.println("Ongelma tietojen hakemisessa.");
        }
        return locale;
    }

    private TypedQuery<User> getUserQuery(EntityManager em, String username){
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query;
    }
}