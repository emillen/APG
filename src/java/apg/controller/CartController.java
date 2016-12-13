/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.controller;

import apg.model.ShoppingCart;
import java.util.List;
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
public class CartController {

    @PersistenceContext(unitName = "APGPU")
    EntityManager em;

    public List<ShoppingCart> getCart(String name) {

        List<ShoppingCart> cart = em.createNamedQuery("ShoppingCart.findByUsername", ShoppingCart.class)
                .setParameter("username", name).getResultList();

        return cart;
    }

    public void buyCart(String name) {
        deleteCart(name);
    }

    public void emptyCart(String name) {
        restoreInventory(name);
        deleteCart(name);
    }

    private void restoreInventory(String name) {
        List<ShoppingCart> cart = getCart(name);
        cart.forEach((item) -> {
            int amount = item.getAmount();
            item.getItem().setAmount(item.getItem().getAmount() + amount);
        });
    }

    private void deleteCart(String name) {
        em.createNamedQuery("ShoppingCart.deleteByUsername", ShoppingCart.class)
                .setParameter("username", name).executeUpdate();
    }
}
