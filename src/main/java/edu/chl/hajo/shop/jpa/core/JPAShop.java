package edu.chl.hajo.shop.jpa.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Shop is a container for other containers
 * NOTE: Uses Java 1.7
 *
 * @author hajo
 */
public class JPAShop implements IShop {
    private final IProductCatalogue productCatalogue;
    private final ICustomerRegistry customerRegistry;
    private final IOrderBook orderBook;

    

    public JPAShop(String persistenceUnitName) {
        Logger.getAnonymousLogger().log(Level.INFO, "Shop alive {0}", this.hashCode());
        productCatalogue = ProductCatalogue.newInstance(persistenceUnitName);
        customerRegistry = CustomerRegistry.newInstance(persistenceUnitName);
        orderBook = OrderBook.newInstance(persistenceUnitName);
    }

    @Override
    public ICustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    @Override
    public IOrderBook getOrderBook() {
        return orderBook;
    }

    @Override
    public IProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }
}
