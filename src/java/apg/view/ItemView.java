/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.view;

import apg.controller.ItemController;
import apg.model.Item;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author daseel
 */
@Named(value = "itemView")
@RequestScoped
public class ItemView implements Serializable {

    @EJB
    private ItemController controller;

    private int id;
    private int amount;
    private boolean fail;
    private boolean success;

    /**
     * Creates a new instance of ItemView
     */
    public ItemView() {
        fail = false;
        success = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFail() {
        return fail;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return controller.getItemById(id);
    }

    public String addToCart() {

        if (controller.addToCart(id, amount)) {
            success = true;
        } else {
            fail = true;
        }
        return "";
    }
    
    public String addToStock(){
        controller.addToStock(id, amount);
        return "";
    }
    
    public String removeItem(){
        controller.removeItem(id);
        return "store.xhtml";
    }
}
