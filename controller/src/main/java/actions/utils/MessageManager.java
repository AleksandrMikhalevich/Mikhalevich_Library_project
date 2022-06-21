package actions.utils;

import java.util.ResourceBundle;

/**
 * @author Alex Mikhalevich
 * @created 2022-05-28 16:16
 * Class to manage messages to display in browser
 */

public class MessageManager {

    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private MessageManager() {

    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
