/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Day;
import entities.Lunch;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@Named(value = "formBean")
@SessionScoped
public class formBean implements Serializable {
    
    @PersistenceContext(unitName="HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private HashMap<Integer,String> list = new HashMap();
    /**
     * Creates a new instance of formBean
     */
    public formBean() {
    }

    public HashMap<Integer, String> getList() {
        return list;
    }

    public void setList(HashMap<Integer, String> list) {
        this.list=list;
    }
    
    public void submit(){
        list.forEach((key, value) -> {
            System.out.println(key + value);
            action(key, value);
        });
    }
    
    public void action(int id, String name){
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Lunch.updateById", Lunch.class).setParameter("id", id).setParameter("name", name).executeUpdate();
            utx.commit();
        } catch (RollbackException ex) {
            Logger.getLogger(formBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicMixedException ex) {
            Logger.getLogger(formBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HeuristicRollbackException ex) {
            Logger.getLogger(formBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(formBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalStateException ex) {
            Logger.getLogger(formBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SystemException ex) {
            Logger.getLogger(formBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotSupportedException ex) {
            Logger.getLogger(formBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public List<Lunch> getLunchDay(int day){
        List<Lunch> resultList = em.createNamedQuery("Lunch.findByDay", Lunch.class).setParameter("day", day).getResultList();
        for (Lunch lunch : resultList) {
            list.put(lunch.getId(), lunch.getName());
        }
        list.forEach((k,v)->{System.out.println(k+" "+v);});
        return resultList;
    }
    
    public List<Day> getDays(){
        return em.createNamedQuery("Day.findAll", Day.class).getResultList();
    }
    
    public void updateName(int id, String name){
        em.createNamedQuery("Lunch.updateById", Day.class).setParameter("id", id).setParameter("name", name);
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
