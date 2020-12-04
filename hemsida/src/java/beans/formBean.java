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
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    public String action(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        return "lunchmeny.xhtml";
    }
   
    public List<Lunch> getLunchDay(int day){
        List<Lunch> resultList = em.createNamedQuery("Lunch.findByDay", Lunch.class).setParameter("day", day).getResultList();
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
