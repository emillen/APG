/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.controller;

import apg.model.Item;
import apg.model.ShoppingCart;
import apg.model.User;
import apg.util.SessionUtils;
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
public class ItemController {

    @PersistenceContext(unitName = "APGPU")
    private EntityManager em;

    public Item getItemById(int id) {

        return em.find(Item.class, id);

    }

    public boolean addToCart(int id, int amount) {

        Item item = em.find(Item.class, id);
        User user = em.createNamedQuery("User.findByUsername", User.class)
                .setParameter("username", SessionUtils.getUserName()).getSingleResult();
        if (item.getAmount() < amount) {
            return false;
        } else {
           // TODO Fix adding item to cart bug (might not care though)
            ShoppingCart cart = new ShoppingCart(user, item, amount);
            item.setAmount(item.getAmount() - amount);
            em.persist(cart);
            em.persist(item);
            return true;
        }
    }    
    
    public  void addToStock(int id, int amount){
            Item item = em.find(Item.class, id);
            
            item.setAmount(item.getAmount() + amount);
    }
    
    public void removeItem(int id){
        
        Item item = em.find(Item.class, id);
        
        em.remove(item);
    }
}
