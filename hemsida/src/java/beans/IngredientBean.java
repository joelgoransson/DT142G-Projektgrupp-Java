/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Ingredient;
import entities.Menuitem;
import entities.Menuitemhasingredient;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.Transactional;

/**
 *
 * @author Joel
 */
@Named(value = "ingredientBean")
@SessionScoped
public class IngredientBean implements Serializable {

    @PersistenceContext(unitName = "HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    List<Menuitemhasingredient> list = new ArrayList<>();
    boolean init = false;
    
    public IngredientBean() {
    }
    
    public void initList(){
        list = new ArrayList<>();
        List<Menuitemhasingredient> resultList = em.createNamedQuery("Menuitemhasingredient.findAll", Menuitemhasingredient.class).getResultList();
        for(Menuitemhasingredient menuitemhasingredient : resultList){
            list.add(menuitemhasingredient);
        }
        int id = findMaxID()+1;
        for(Menuitem item: getMenuItems()){
            Menuitemhasingredient mihi = new Menuitemhasingredient();
            mihi.setId(id);
            id = id + 1;
            mihi.setMenuitemname(item.getName());
            mihi.setIngredientname("");
            mihi.setQuantity(0);
            list.add(mihi);
        }
    }
    
    public List<Menuitem> getMenuItems(){
        List<Menuitem> resultList = em.createNamedQuery("Menuitem.findAll", Menuitem.class).getResultList();
        return resultList;
    }
    
    public Integer findMaxID(){
        List<Menuitemhasingredient> resultList = em.createNamedQuery("Menuitemhasingredient.sortByID", Menuitemhasingredient.class).getResultList();
        if(resultList.size() <= 0){
            return 0;
        }
        return resultList.get(0).getId();
    }
    
    public List<Menuitemhasingredient> getMihiFromIngredients(String name){
        if(init == false){
            init = true;
            initList();
        }
        List<Menuitemhasingredient> mihis = new ArrayList<>();
        for(Menuitemhasingredient item : list){
            if(item.getMenuitemname().equals(name)){
                mihis.add(item);
            }
        }
        return mihis;
    }
    
    private void deleteAllMihi(){
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Menuitemhasingredient.deleteAll", Menuitemhasingredient.class).executeUpdate();
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(IngredientBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void submit(){
        deleteAllMihi();
        for(Menuitemhasingredient mihi: list){
            List<Menuitem> resultList = em.createNamedQuery("Ingredient.findByName", Menuitem.class).setParameter("name", mihi.getIngredientname()).getResultList();
            if(!"".equals(mihi.getIngredientname())){
                persist(mihi);
            }
            if(resultList.isEmpty()){
                Ingredient ingredient = new Ingredient();
                ingredient.setAmount(5);
                ingredient.setName(mihi.getIngredientname());
                if(!"".equals(ingredient.getName())){
                    persist(ingredient);
                }
            }
        }
        /*Refresh*/
        initList();
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException ex) {
            Logger.getLogger(IngredientBean.class.getName()).log(Level.SEVERE, null, ex);
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
