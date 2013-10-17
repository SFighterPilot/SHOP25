package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.utils.AbstractEntity;
import javax.persistence.Entity;

/**
 * A Product
 * @author hajo, Modified by Fredrik Brüdigam
 */
@Entity
public class Product extends AbstractEntity {

    public enum Category{
        ACTION,
        RPG,
        MMORPG,
        RACING,
        STRATEGY,
    }
    
    private String name;
    private double price;
    private Category cat;
    
    public Product() {
    }
    
    public Product(String name, double price, Category cat) {
        this.name = name;
        this.price = price;
        this.cat = cat;
    }

    public Product(Long id, String name, double price, Category cat) {
        super(id);
        this.name = name;
        this.price = price;
        this.cat = cat;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
    
    public Category getCategory(){
        return cat;
    }
     
    @Override
    public String toString() {
        return "Product{" + "id=" + getId() + ", name=" + name + ", price=" + price + ", cat="+ cat + '}';
    }
}
