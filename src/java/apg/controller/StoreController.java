/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.controller;

import apg.model.Item;
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
public class StoreController {

    @PersistenceContext(unitName = "APGPU")
    EntityManager em;

    public StoreController() {
    }

    public List<Item> getItems() {
        return em.createNamedQuery("Item.findAll", Item.class).getResultList();
    }

    public void addToInventory(String name, String description, double price, int amount) {
        Item item = new Item(name, description, price, amount);
        em.persist(item);

    }
}
