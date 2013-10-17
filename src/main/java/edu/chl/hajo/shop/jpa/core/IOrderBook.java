
package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.utils.IDAO;
import java.util.List;

/**
 * Interface to order book
 * @author hajo
 */
public interface IOrderBook extends IDAO<PurchaseOrder, Long> {

    List<PurchaseOrder> getByCustomer(Customer c);

    public int removeOrderItem(OrderItem oi);
    
}
