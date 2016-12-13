/*
 *  This file is part of a school assignment
 *  Feel free to use my shitty code
 * 
 */
package apg.view;

import apg.controller.UserController;
import apg.model.User;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author daseel
 */
@Named(value = "blockView")
@RequestScoped
public class BlockView {

    @EJB
    private UserController controller;
    private int id;

    /**
     * Creates a new instance of BlockView
     */
    public BlockView() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String block() {
        if (id != 0) {
            controller.block(id);
        }
        return "";
    }

    public List<User> getUserList() {
        
       
        return controller.getUserList();
    }
}
