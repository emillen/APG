/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author daseel
 */
@Entity
@Table(name = "SHOPPING_CART")
@NamedQueries({
    @NamedQuery(name = "ShoppingCart.findByUsername", query = "SELECT s FROM ShoppingCart s WHERE s.user.username = :username")
    ,
    @NamedQuery(name = "ShoppingCart.deleteByUsername", query = "DELETE  FROM  ShoppingCart s WHERE s.user.username like :username")
})
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private int amount;
    @JoinColumn(name = "ITEM", referencedColumnName = "ID")
    @ManyToOne
    private Item item;
    @JoinColumn(name = "USER", referencedColumnName = "ID")
    @ManyToOne
    private User user;

    public ShoppingCart() {
    }

    public ShoppingCart(Integer id) {
        this.id = id;
    }

    public ShoppingCart(User user, Item item, int amount) {
        this.user = user;
        this.item = item;
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
