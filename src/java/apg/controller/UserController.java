/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.controller;

import apg.exception.BannedException;
import apg.model.User;
import apg.util.SessionUtils;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author daseel
 */
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Stateless
public class UserController {

    @PersistenceContext(unitName = "APGPU")
    private EntityManager em;

    @EJB 
    private CartController cc;

    public UserController() {
    }

    public boolean validateUser(String username, String password) throws BannedException{

        User user;

        try {
            user = em.createNamedQuery("User.findByUsername", User.class)
                    .setParameter("username", username).getSingleResult();
        } catch (Exception e) {
            return false;
        }
        if (user.getPassword().equals(password)) {
            
            if(user.isBanned())
                throw new BannedException("User is banned");
            
            setSessionAttributes(user);
            return true;
        }

        return false;
    }

    public boolean register(String name, String password) {

        try {
            User user = em.createNamedQuery("User.findByUsername", User.class)
                    .setParameter("username", name).getSingleResult();
            System.out.println("Vi är under");
            return false;
        } catch (Exception e) {
            User user = new User(name, password);
            em.persist(user);
            return true;
        }
    }

    public void block(int id) {
        System.out.println("over block");
        User user = em.find(User.class, id);
        cc.emptyCart(user.getUsername());
        user.setBanned(true);
        System.out.println("beneath block");
    }

    public void logOut() {

        SessionUtils.getSession().removeAttribute(SessionUtils.USERNAME);
        SessionUtils.getSession().removeAttribute(SessionUtils.ADMIN);
        SessionUtils.getSession().removeAttribute(SessionUtils.BANNED);
    }

    private void setSessionAttributes(User user) {
        SessionUtils.getSession().setAttribute(SessionUtils.USERNAME, user.getUsername());
        SessionUtils.getSession().setAttribute(SessionUtils.ADMIN, user.isAdmin());
        SessionUtils.getSession().setAttribute(SessionUtils.BANNED, user.isBanned());
    }

    public List<User> getUserList() {

        return em.createNamedQuery("User.findAll", User.class).getResultList();
    }
}
