package actions.utils;

import java.util.ResourceBundle;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 16:17
 * Class to manage pages to display in browser
 */
public class PageManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("pages");

    private PageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
