package apg.view;

import apg.controller.CartController;
import apg.model.ShoppingCart;
import apg.util.SessionUtils;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author daseel
 */
@Named(value = "cartView")
@RequestScoped
public class CartView implements Serializable {

    @EJB
    private CartController controller;

    /**
     * Creates a new instance of CartView
     */
    public CartView() {
    }

    public List<ShoppingCart> getCart() {
        return controller.getCart(SessionUtils.getUserName());
    }

    public String dropItem(ShoppingCart cart) {

        return "";
    }

    public String emptyCart() {
        System.out.println("empty1");

        controller.emptyCart(SessionUtils.getUserName());
        return "";
    }

    public String buyCart() {
        controller.buyCart(SessionUtils.getUserName());
        return "";
    }
}
