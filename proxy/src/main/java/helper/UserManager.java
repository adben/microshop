package helper;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

/**
 * Created by Jean-Baptiste Clion on 01.06.2016.
 * Authentication service used to authenticate a given user
 *
 * @author Jean-Baptiste Clion - jbclion@gmail.com
 * @version 0.1
 */
public class UserManager {

    /**
     * Authenticate the current application user
     * @return The current user id if authenticated else null
     */
    public static String getCurrentUser(){
        String toReturn = null;

        UserService userService = UserServiceFactory.getUserService();

        if(userService != null) {

            User currentUser = userService.getCurrentUser();

            if(currentUser != null) {

                String user = currentUser.getUserId();

                if (user != null) {

                    toReturn = user;

                }
            }
        }

        return toReturn;
    }



}