package com.automaatio.utils;

import java.util.Locale;

/**
 * The CurrentLocale class provides methods for checking which locale is currently
 * used in the application.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class CurrentLocale {

    /**
     * Returns the user's currently selected locale from the cache.
     * If no user is logged in, the current temporary locale is returned.
     *
     * @return The locale currently in use
     */
    public Locale getCurrentLocale() {
        CacheSingleton cache = CacheSingleton.getInstance();
        if (cache.getUser() == null) {
            return cache.getTempLocale();
        } else {
            return (new BundleLoader()).loadResourceByUsersLocale().getLocale();
        }
    }
}