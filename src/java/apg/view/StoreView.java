/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.view;

import apg.controller.StoreController;
import apg.model.Item;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author daseel
 */
@Named(value = "storeView")
@RequestScoped
public class StoreView implements Serializable {

    @EJB
    private StoreController controller;
     
    /**
     * Creates a new instance of StoreView
     */
    public StoreView() {
    }

    public List<Item> getItems() {
        return controller.getItems();
    }
}
