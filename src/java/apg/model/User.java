/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apg.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author daseel
 */
@Entity
@Table(name = "USER")
@NamedQueries({
    @NamedQuery(
            name = "User.findAll",
            query = "SELECT u FROM User u"
    )
    , @NamedQuery(
            name = "User.findByUsername",
            query = "SELECT u FROM User u WHERE u.username = :username"
    )
})
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String username;
    @Column
    private String password;
    @Column
    private Boolean banned = false;
    @Column
    private Boolean admin = false;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<ShoppingCart> shoppingCart;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId(){
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isBanned() {
        return banned;
    }

    public void setBanned(Boolean banned) {
        this.banned = banned;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<ShoppingCart> getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCartCollection(List<ShoppingCart> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void addItemToCart() {

    }
}
