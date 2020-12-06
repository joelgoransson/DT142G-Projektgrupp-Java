/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Menuitem;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<String> typeList;
    private Menuitem newItem;
    /**
     * Creates a new instance of CarteBean
     */
    public MenuBean() {
        list = new ArrayList<>();
        typeList = Arrays.asList("förrätt", "huvudrätt", "efterrätt", "dryck");
        newItem = new Menuitem();
    }

    public void init(){
        try {
            utx.begin();
            em.joinTransaction();
            list = em.createNamedQuery("Menuitem.findAll", Menuitem.class).getResultList();
            utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Menuitem> getOneType(String type){
        List<Menuitem> ret = new ArrayList<>();
        for(Menuitem item : list){
            System.out.println(item.getType());
            if(item.getType().equalsIgnoreCase(type)){
                ret.add(item);
            }
        }
        return ret;
    }
    
    public void submit(){
        System.out.println(newItem.getName());
        try {
            utx.begin();
            em.joinTransaction();
            em.persist(newItem);
            utx.commit();   
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
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
