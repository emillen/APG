/*
 *  This file is part of a school assignment
 *  Feel free to use my shitty code
 * 
 */
package apg.view;

import apg.controller.StoreController;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author daseel
 */
@Named(value = "adminManager")
@RequestScoped
public class AdminManager {

    @EJB
    private StoreController controller;
    
    private String itemname;
    private String description;
    private double price;
    private int amount;
    
    /**
     * Creates a new instance of AdminManager
     */
    public AdminManager() {
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    
    public String addToInventory(){
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
        System.out.println(itemname);
        System.out.println(description);
        System.out.println(price);
        System.out.println(amount);
        
        controller.addToInventory(itemname, description, price, amount);
        return "";
    }
    
}
