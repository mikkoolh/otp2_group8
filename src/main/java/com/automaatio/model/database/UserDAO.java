package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Locale;

/**
 * The UserDAO class represents a DAO (Data Access Object)
 * for carrying out database operations related to the User entity.
 *
 * @author Mikko Hänninen, Nikita Nossenko, Matleena Kankaanpää
 * @version 1.0 15.9.2023
 */

public class UserDAO implements IDAO {

    /**
     * Adds a new user to the database
     *
     * @param user A new user
     */
    public void addObject(Object user) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist((User) user);
        em.getTransaction().commit();
    }

    /**
     * Adds a new user to the database and returns it.
     *
     * @param object    A new user
     * @return          The new user that was added to the database
     */
    public User addAndReturnObject(Object object) {
        User savedUser;

        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            savedUser = em.merge((User) object);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
        return savedUser;
    }

    /**
     * This method is not used in this class.
     */
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

    /**
     * Deletes a user from the database.
     *
     * @param username    The username of the user to be deleted
     */
    public void deleteObject(String username) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            User user = em.find(User.class, username);
            if (user != null) {
                em.remove(user);
                System.out.println("User " + username + " deleted");
            } else {
                throw new IllegalArgumentException("User with id  " + username + " was not found");
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
     * This method is not used in this class.
     */
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
     * Fetches a user by username.
     *
     * @param username  The username of the user to find
     * @return          User object with the specified username or null if not found
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

    /**
     * Updates a user's password.
     *
     * @param username      The username of the user whose password to update
     * @param oldPassword   The old hashed password
     * @param newPassword   The new hashed password
     */
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
     * Fetches all users from the database
     *
     * @return  A list of users
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
     * Deletes all users from the database
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

    /**
     * Updates the max electricity price set by the user
     *
     * @param price     A new max price
     * @param username  The username of the user to update
     */
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

    /**
     * Updates a user's profile picture.
     *
     * @param username          The username of the user to update
     * @param selectedPictureId An integer identifying a new profile picture
     */
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

    /**
     * Updates the user's selected locale.
     *
     * @param username  The username of the user to update
     * @param locale    A new locale
     */
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

    /**
     * Returns the user's selected locale
     *
     * @param username  The username of the user whose locale to fetch
     * @return          The user's selected locale or null if the user was not found
     */
    public Locale getLocale(String username) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            User user = getUserQuery(em, username).getSingleResult();
            if (user != null && user.getLocale() != null) {
                String localeString = user.getLocale().toString();
                String[] parts = localeString.split("_");
                if (parts.length == 2) {
                    String language = parts[0];
                    String country = parts[1];
                    em.getTransaction().commit();
                    return new Locale(language, country);
                } else {
                    throw new IllegalArgumentException("Locale format in database is incorrect");
                }
            } else {
                throw new NoResultException("User not found");
            }
        } catch (NoResultException e) {
            em.getTransaction().rollback();
            System.out.println("User not found: " + username);
            return new Locale("en", "US");  //Jos käyttäjää ei löydy niin oletuskieli englanti
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Error fetching locale for user: " + username);
            throw e;
        } finally {
            em.close();
        }
    }

    private TypedQuery<User> getUserQuery(EntityManager em, String username){
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        return query;
    }
}