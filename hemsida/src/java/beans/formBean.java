/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Lunch;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Joel
 */
@Named(value = "formBean")
@SessionScoped
public class formBean implements Serializable {
    
    @PersistenceContext(unitName="HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    /**
     * Creates a new instance of formBean
     */
    public formBean() {
    }
    public Integer getLunchCount(){
        TypedQuery<Lunch> messageQuery = em.createNamedQuery("Lunch.findAll", Lunch.class);
        List<Lunch> resultList = messageQuery.getResultList();
        return resultList.size();
    }
    
    
    
    /*
    public List<Lunch> getLunch(){
        TypedQuery<Lunch> messageQuery = em.createNamedQuery("Message.findAll", Lunch.class);
        List<Lunch> resultList = messageQuery.getResultList();
        return resultList;
    }*/

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }
}
