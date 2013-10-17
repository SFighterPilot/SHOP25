package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * All orders
 *
 * @author hajo
 */
public final class OrderBook extends AbstractDAO<PurchaseOrder, Long>
        implements IOrderBook {

    // Factory method
    public static IOrderBook newInstance(String puName) {
        return new OrderBook(puName);
    }

    private OrderBook(String puName) {
        super(PurchaseOrder.class, puName);
    }

    @Override
    public List<PurchaseOrder> getByCustomer(Customer c) {
        List<PurchaseOrder> found = new ArrayList<>();
        for (PurchaseOrder po : getRange(0, getCount())) {
            if (po.getCustomer().equals(c)) {
                found.add(po);
            }
        }
        return found;
    }
    
    @Override
    public int removeOrderItem(OrderItem oi) {
        EntityManager em = null;
        em = super.getEntityManager();
        Query q = em.createQuery("delete from OrderItem oi where oi.id= :id");
        q.setParameter("id", oi.getId());
        em.getTransaction().begin();
        int affectedRows = q.executeUpdate();
        return affectedRows;
    }
}
