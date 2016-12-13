/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.view;

import apg.controller.UserController;
import apg.util.SessionUtils;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author daseel
 */
@Named(value = "userManager")
@RequestScoped
public class UserManager implements Serializable {

    @EJB
    private UserController controller;

    /**
     * Creates a new instance of NavManager
     */
    public UserManager() {
    }

    public String getUsername() {
        String username;
        try {
            username = SessionUtils.getUserName();
        } catch (Exception e) {
            return null;
        }

        return username;
    }

    public String logOut() {
        controller.logOut();
        return "";
    }

    public boolean isAdmin() {

        boolean isAdmin;
        try {
            isAdmin = SessionUtils.isAdmin();
        } catch (IllegalStateException e) {
            isAdmin = false;
        }

        return isAdmin;
    }
}
