package com.automaatio.utils;

import com.automaatio.model.database.UserDAO;
import java.util.ResourceBundle;

/**
 * The BundleLoader class provides utility methods for loading ResourceBundles.
 *
 * @author Mikko Hänninen, Nikita Nossenko, Elmo Erla, Matleena Kankaanpää
 * @version 1.0
 */

public class BundleLoader {
    private UserDAO userDAO = new UserDAO();
    private CacheSingleton cache = CacheSingleton.getInstance();

    /**
     * Fetches the currently logged in user from the cache, then fetches the user's
     * selected locale from the database and returns the corresponding ResourceBundle.
     *
     * @return The ResourceBundle corresponding to the user's selected locale
     */
    public ResourceBundle loadResourceByUsersLocale() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", userDAO.getLocale(cache.getUser().getUsername()));
        return resourceBundle;
    }
}
