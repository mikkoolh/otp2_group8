package com.automaatio.utils;

import java.util.Locale;

public class CurrentLocale {

    /**
     * Returns the locale currently in use
     * @return The current user's locale if logged in, or the temporary locale if not logged in
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