
package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.utils.IDAO;
import java.util.List;

/**
 * Interface to product catalogue
 * @author hajo, Modified by Fredrik Brüdigam
 */
public interface IProductCatalogue extends IDAO<Product, Long> {

    public List<Product> getByName(String name);
    
    public List<Product> getByCategory(Product.Category cat);
}
