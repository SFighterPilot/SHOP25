package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;

/**
 * All products
 *
 * @author hajo, Modified by Fredrik Brüdigam
 */
public final class ProductCatalogue extends AbstractDAO<Product, Long>
        implements IProductCatalogue {

    //private ProductCatalogue() {
    //}
    
    public ProductCatalogue(String puName) {
        super(Product.class, puName);
    }
    
    //Factory method
    public static IProductCatalogue newInstance(String puName) {
      return new ProductCatalogue(puName);
    }

    @Override
    public List<Product> getByName(String name) {
        List<Product> found = new ArrayList<>();
        for (Product p : getRange(0, getCount())) {
            if (p.getName().equals(name)) {
                found.add(p);
            }
        }
        return found;
    }

    @Override
    public List<Product> getByCategory(Product.Category cat) {
        
        List<Product> foundByCategory = new ArrayList<>();
        for(Product p : getRange(0,getCount())){
            if(p.getCategory().equals(cat)){
                foundByCategory.add(p);
            }
        }
        
        return foundByCategory;
    }
}
