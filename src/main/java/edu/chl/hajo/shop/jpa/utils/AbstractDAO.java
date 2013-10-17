package edu.chl.hajo.shop.jpa.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * A container for entities, base class for OrderBook, ProductCatalogue,
 * CustomerRegistry The fundamental common operations are here (CRUD).
 *
 * T is type for items in container K is type of id (primary key)
 *
 * @author hajo
 */
public abstract class AbstractDAO<T, K>
        implements IDAO<T, K> {

    //private List<T> elems = new ArrayList<>();
    
    private EntityManagerFactory emf;
    private final Class<T> clazz;
    protected AbstractDAO(Class<T> clazz, String puName) {
        this.clazz = clazz;
        emf = Persistence.createEntityManagerFactory(puName);
    }
    
    protected EntityManager getEntityManager() {
        EntityManager em = emf.createEntityManager();
        Logger.getAnonymousLogger().log(Level.INFO, "Createing EM {0}", em);
        return em;
    }
    
    @Override
    public void add(T t) {
        /*
        if (t == null) {
            throw new IllegalArgumentException("Nulls not allowed");
        }
        elems.add(t);
        * */
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    

    @Override
    public void remove(K id) {
        /*T t = find(id);
        if (t != null) {
            elems.remove(t);
        }
        * */
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            T t = em.getReference(clazz, id);
            em.remove(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public T update(T t) {
        /*T old = find(t.getId());
        if (old != null) {
            elems.remove(old);
        }
        elems.add(t);
        * */
        
        EntityManager em = null;
        T up = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            up = em.merge(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return up;
    }

    @Override
    public T find(K id) {
        /*
        for (T t : elems) {
            if (t.getId().equals(id)) { // NOTE: equals, not ==
                return t;
            }
        }
        * */
        EntityManager em = emf.createEntityManager();
        T p = em.find(clazz, id);
        return p;
    }

    @Override
    public List<T> getRange(int first, int nItems) {
        // From inclusive, to exclusive
        //return elems.subList(first, first + nItems);
        EntityManager em = emf.createEntityManager();
        List<T> products = new ArrayList<>();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clazz));
            Query q = em.createQuery(cq);
            
                q.setMaxResults(nItems);
                q.setFirstResult(first);
            
            products.addAll(q.getResultList());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            em.close();
        }
        return products;
    }

    @Override
    public int getCount() {
        //return elems.size();
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(clazz)));
        int i = (em.createQuery(cq).getSingleResult()).intValue();
        em.close();
        return i;
    }
    
    

    /*
    @Override
    public void sort(Comparator<T> comp) {
        Collections.sort(elems, comp);
    }
    * */
}
