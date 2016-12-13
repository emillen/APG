package apg.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Helper-class to check user privileges
 *
 * @author daseel
 */
public class SessionUtils {

    public static final String USERNAME = "username";
    public static final String ADMIN = "admin";
    public static final String BANNED = "banned";

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = getSession();

        if (!validUserSession(session)) {
            return null;
        }

        return session.getAttribute(USERNAME).toString();
    }

    public static boolean isAdmin() {
        HttpSession session = getSession();

        if (!validUserSession(session)) {
            throw new IllegalStateException("not logged in");
        }
        
        System.out.println("HERE WE ARE BOIIIIIIIIII........................................................................-------------------------------" + session.getAttribute(ADMIN));
        
        boolean isBanned = (Boolean) session.getAttribute(ADMIN);
        return isBanned;
    }

    public static boolean isBanned() {
        HttpSession session = getSession();

        if (!validUserSession(session)) {
            throw new IllegalStateException("not logged in");
        }
        
        System.out.println();
        
        boolean isBanned = getBoolFromString(BANNED, session);
        return isBanned;
    }

    private static boolean getBoolFromString(String string, HttpSession session) {
        return Boolean.parseBoolean(session.getAttribute(string).toString());
    }

    private static boolean validUserSession(HttpSession session) {
        return (session != null && session.getAttribute(USERNAME) != null);
    }
}
