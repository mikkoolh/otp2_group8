package com.automaatio.utils;

import com.automaatio.model.database.UserDAO;

import java.util.ResourceBundle;

public class BundleLoader {
    private UserDAO userDAO = new UserDAO();
    private CacheSingleton cache = CacheSingleton.getInstance();

    public ResourceBundle loadResourceByUsersLocale() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", userDAO.getLocale(cache.getUser().getUsername()));
        return resourceBundle;
    }
}
