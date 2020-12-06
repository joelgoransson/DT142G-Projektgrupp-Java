/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Lunch;
import entities.Menuitem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.SessionScoped;
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
@Named(value = "carteBean")
@SessionScoped
public class CarteBean {
    private List<Menuitem> list = new ArrayList<>();
    
    @PersistenceContext(unitName = "HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    /**
     * Creates a new instance of CarteBean
     */
    public CarteBean() {
    }

    public List<Menuitem> getList() {
        return list;
    }

    public void setList(List<Menuitem> list) {
        this.list = list;
    }
    
    public void createHashmap(){
        try {
            utx.begin();
            em.joinTransaction();
            list = em.createNamedQuery("Menuitem.findAll", Menuitem.class).getResultList();
            utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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
