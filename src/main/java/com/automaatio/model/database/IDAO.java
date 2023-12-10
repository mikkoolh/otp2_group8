package com.automaatio.model.database;

import java.util.List;

/**
 * The IDAO interface defines a common interface for all DAOs
 * (Data Access Objects) for carrying out database operations.
 *
 * @author Mikko Hänninen
 * @version 1.0
 */

public interface IDAO {

    /**
     * Adds a new object to the database.
     *
     * @param object A new object to be added
     */
    void addObject(Object object);

    /**
     * Deletes an object from the database.
     *
     * @param id The id of the object to be deleted
     */
    void deleteObject(int id);

    /**
     * Fetches an object identified by an integer from the database.
     *
     * @param id The id of the object to be fetched
     * @return The object with the id, or null if not found
     */
    Object getObject(int id);

    /**
     * Fetches an object identified by a String from the database.
     *
     * @param s The id of the object to be fetched
     * @return The object with the id, or null if not found
     */
    Object getObject(String s);

    /**
     * Returns all objects from a table.
     *
     * @return All objects from a table
     */
    List getAll();
}
