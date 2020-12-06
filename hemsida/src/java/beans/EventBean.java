/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Event;
import entities.Lunch;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author Joel
 */
@Named(value = "eventBean")
@SessionScoped
public class EventBean implements Serializable {

    @PersistenceContext(unitName = "HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    Event newEvent;
    
    public void createEvent(){
        newEvent = new Event();
    }

    public Event getNewEvent() {
        return newEvent;
    }

    
    private ArrayList<Event> list;
    
     public void createHashmap(){ 
       try {
            utx.begin();
            em.joinTransaction();
            list = new ArrayList<Event>(em.createNamedQuery("Event.findAll", Event.class).getResultList());
            utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void submit(){
        newEvent.setId(findMaxID()+1);
        try {
            utx.begin();
            em.joinTransaction();
            em.persist(newEvent);
            utx.commit();   
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer findMaxID(){
        return em.createNamedQuery("Event.sortByID", Event.class).getResultList().get(0).getId();
    }

    public List<Event> getEvents(){
        return em.createNamedQuery("Event.findAll", Event.class).getResultList();
    }
    
    /**
     * Creates a new instance of EventBean
     */
    public EventBean() {
    }

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
