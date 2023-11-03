package com.automaatio.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class CompoundMessageCreator {

    /**
     * Fills a template string with arguments
     * @param args An Object holding message arguments
     * @param template A string template
     * @return A compound string
     */
    public String create(Object[] args, String template) {
        Locale locale = (new CurrentLocale().getCurrentLocale());
        ResourceBundle resourceBundle = ResourceBundle.getBundle("TextResources", locale);
        MessageFormat formatter = new MessageFormat("");
        formatter.setLocale(locale);
        formatter.applyPattern(resourceBundle.getString(template));
        return formatter.format(args);
    }
}