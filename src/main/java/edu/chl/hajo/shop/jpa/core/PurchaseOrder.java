package edu.chl.hajo.shop.jpa.core;

import edu.chl.hajo.shop.jpa.utils.AbstractEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * A purchase order
 *
 * @author hajo
 */

@Entity
public class PurchaseOrder extends AbstractEntity {
    // State of order
    public enum State {

        ACCEPTED,
        CANCELLED,
        INVOICED,
        UNINVOIDED,
        SHIPPED,
    }
    @Temporal(TemporalType.DATE)
    private Date orderDate = new Date();
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="ORDERITEMS")
    private List<OrderItem> items;
    @ManyToOne
    private Customer customer;
    private State orderState = State.ACCEPTED;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
    }

    public PurchaseOrder(Long id, Customer customer, List<OrderItem> items) {
        super(id);
        this.customer = customer;
        this.items = items;
    }

    public Date getDate() {
        return orderDate;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public State getState() {
        return orderState;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "id=" + getId() + ", date=" + orderDate
                + ", items=" + items + ", customer=" + customer
                + ", state=" + orderState + '}';
    }
}
