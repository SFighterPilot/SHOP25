
package edu.chl.hajo.shop.jpa.utils;

import java.util.Objects;
//import java.util.Random;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Base class for all entities (later to be stored in database), 
 * Product, Order, etc
 * @author hajo
 */
@MappedSuperclass
public abstract class AbstractEntity{

    @Id
    @GeneratedValue
    private Long id; 
   
    
    protected AbstractEntity(){
        // This is for now, later database will generate
        //this.id = new Long(new Random().nextInt(1000));
    }
    
    protected AbstractEntity(Long id){
        this.id = id;
    }
    
    public Long getId(){
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractEntity other = (AbstractEntity) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
