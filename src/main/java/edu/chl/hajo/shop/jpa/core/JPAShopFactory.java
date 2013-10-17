package edu.chl.hajo.shop.jpa.core;

/**
 * Static factory for Shops
 *
 * @author hajo
 */
public class JPAShopFactory {

    private JPAShopFactory() {
    }

    // If initTestData there will be some data to use
    public static IShop getShop(String persistenceUnitName) {
        JPAShop s = new JPAShop(persistenceUnitName);
        
        return s;
    }

    // If we have no database we can use this
    private static void initTestData(JPAShop shop) {

        // Add new data
        shop.getProductCatalogue().add(new Product("Lightbulb", 11.11,Product.Category.ACTION));
        shop.getProductCatalogue().add(new Product("GTA V", 22.22,Product.Category.RACING));
        shop.getProductCatalogue().add(new Product("AMD FX 8350", 33.33,Product.Category.MMORPG));
        shop.getProductCatalogue().add(new Product("DOTA", 44.44,Product.Category.ACTION));
        shop.getProductCatalogue().add(new Product("Pillowcase", 55.55, Product.Category.STRATEGY));
        shop.getProductCatalogue().add(new Product("INTEL i5 4670K", 66.66,Product.Category.STRATEGY));
        
        shop.getCustomerRegistry().add(new Customer(new Address("aaa", 1, "aaa"),
                "arne", "arnesson", "arne@gmail.com"));
        shop.getCustomerRegistry().add(new Customer(new Address("bbbb", 2, "bbb"),
                "berit", "beritsson", "berit@gmail.com"));
        shop.getCustomerRegistry().add(new Customer(new Address("ccc", 3, "ccc"),
                "cecilia", "cecilia", "cecila@gmail.com"));

        Customer c = shop.getCustomerRegistry().getByName("arne").get(0);
        c.addProductToCart(shop.getProductCatalogue().getByName("banana").get(0));
        c.addProductToCart(shop.getProductCatalogue().getByName("apple").get(0));
        c.addProductToCart(shop.getProductCatalogue().getByName("pear").get(0));

        shop.getOrderBook().add(new PurchaseOrder(c, c.getCart().getAsOrderItems()));

    }
}
