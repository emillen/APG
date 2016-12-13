/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.view;

import apg.controller.UserController;
import apg.exception.BannedException;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author daseel
 */
@Named(value = "loginView")
@RequestScoped
public class LoginView implements Serializable {

    @EJB
    UserController controller;

    private String username;
    private String password;
    private String errormsg;
    private boolean success;

    public LoginView() {
        success = false;
    }

    public String getErrormsg() {
        return errormsg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String validate() {

        try {
            if (!controller.validateUser(username, password)) {
                errormsg = "username and password does not match";

            }
            success = true;
            return "index.xhtml";
        } catch (BannedException e) {
            errormsg = "You are banned sir";
        }

        return "";
    }

    public String register() {

        if (!controller.register(username, password)) {
            errormsg = "username is taken!";
            return "";
        }
        success = true;
        return "";
    }

    public boolean success() {
        return success;
    }
}
