/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Ingredient;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named(value = "inventarieBean")
@SessionScoped
public class InventarieBean implements Serializable{

    @PersistenceContext(unitName = "HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    final List<Ingredient> list = new ArrayList<>();
    
    /**
     * Creates a new instance of InventarieBean
     */
    public InventarieBean() {
    }
    
    public void init(){
        list.clear();
        List<Ingredient> resultList = em.createNamedQuery("Ingredient.findAll", Ingredient.class).getResultList();
        for(Ingredient ingredient: resultList){
            list.add(ingredient);
        }
    }
    
    public List<Ingredient> getIngredients(){
        if(list.isEmpty()){
            init();
        }
        return list;
    }
    
    public void deleteAll(){
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Ingredient.deleteAll", Ingredient.class).executeUpdate();
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(IngredientBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void submit(){
        deleteAll();
        System.out.println(list.size());
        for(Ingredient item: list){
            persist(item);
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
