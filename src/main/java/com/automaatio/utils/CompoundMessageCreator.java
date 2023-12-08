package com.automaatio.utils;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The CompoundMessageCreator class provides methods for filling string templates
 * from the resource bundle with arguments defined at runtime.
 *
 * @author Matleena Kankaanpää
 * @version 1.0
 */

public class CompoundMessageCreator {

    /**
     * Fills a template string with the provided arguments.
     * The method checks the user's currently selected locale with an instance of
     * the CurrentLocale class and fetches the template from the corresponding
     * resource bundle.
     *
     * @param args An Object holding message arguments
     * @param template A string template to be filled
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