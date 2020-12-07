/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Weekday;
import entities.Lunch;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author Joel
 */
@Named(value = "lunchBean")
@SessionScoped
public class LunchBean implements Serializable {
    
    @PersistenceContext(unitName="HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private HashMap<Integer,String> list = new HashMap();
    /**
     * Creates a new instance of formBean
     */
    public LunchBean() {
    }
    
    public void createHashmap(){
       try {
            utx.begin();
            em.joinTransaction();
            List<Lunch> resultList = em.createNamedQuery("Lunch.findAll", Lunch.class).getResultList();
            for (Lunch lunch : resultList) {
                 list.put(lunch.getId(), lunch.getName());
            }
            utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<Integer, String> getList() {
        return list;
    }

    public void setList(HashMap<Integer, String> list) {
        this.list=list;
    }
    
    public void submit(){
        list.forEach((key, value) -> {
            //System.out.println(key + value);
            action(key, value);
        });
    }
    
    public void action(int id, String name){
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Lunch.updateById", Lunch.class).setParameter("id", id).setParameter("name", name).executeUpdate();
            utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public List<Lunch> getLunchDay(int day){
        List<Lunch> resultList = em.createNamedQuery("Lunch.findByDay", Lunch.class).setParameter("day", day).getResultList();
        /*for (Lunch lunch : resultList) {
            list.put(lunch.getId(), lunch.getName());
        }
        list.forEach((k,v)->{System.out.println(k+" "+v);});*/
        return resultList;
    }
    
    public List<Lunch> getLunchToday(){
        // Used to display todays lunch offer
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_WEEK);
        day = day-2;
        List<Lunch> resultList = em.createNamedQuery("Lunch.findByDay", Lunch.class).setParameter("day", day).getResultList();
        return resultList;
    }
    
    public List<Weekday> getDays(){
        return em.createNamedQuery("Weekday.findAll", Weekday.class).getResultList();
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
