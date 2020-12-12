/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Menuitem;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

/**
 *
 * @author joaki
 */
@Named(value = "menuBean")
@SessionScoped
public class MenuBean implements Serializable {  
    
    @PersistenceContext(unitName = "HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    private List<Menuitem> list;
    private final List<String> typeList;
    private final List<String> foodTypeList;
    private Menuitem newItem;
    /**
     * Creates a new instance of CarteBean
     */
    public MenuBean() {
        typeList = Arrays.asList("Förrätt", "Huvudrätt", "Efterrätt", "Dryck");
        foodTypeList = Arrays.asList("Förrätt", "Huvudrätt", "Efterrätt");
    }

    public void init(){
        list = em.createNamedQuery("Menuitem.findAll", Menuitem.class).getResultList();
        newItem = new Menuitem();
    }
    
    public List<Menuitem> getOneType(String type){
        return em.createNamedQuery("Menuitem.findByType", Menuitem.class).setParameter("type", type).getResultList();
    }
    
    public List<Menuitem> getDrink(){
        return em.createNamedQuery("Menuitem.findByType", Menuitem.class).setParameter("type", "Dryck").getResultList();
    }
    
    public void submit(){
        persist(newItem);
    }
    
    public void delete(String name){
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Menuitem.deleteByName", Menuitem.class).setParameter("name", name).executeUpdate();
            utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Menuitem> getList() {
        return list;
    }

    public void setList(List<Menuitem> list) {
        this.list = list;
    }

    public List<String> getTypeList() {
        return typeList;
    }
    
    public List<String> getFoodTypeList() {
        return foodTypeList;
    }

    public Menuitem getNewItem() {
        return newItem;
    }

    public void setNewItem(Menuitem newItem) {
        this.newItem = newItem;
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
