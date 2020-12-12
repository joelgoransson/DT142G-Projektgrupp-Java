/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Employee;
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
@Named(value = "employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {

    @PersistenceContext(unitName = "HemsidaPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    final List<Employee> list = new ArrayList<>();
   
    
    /**
     * Creates a new instance of EmployeeBean
     */
    public EmployeeBean() {
    }
    
    public void init(){
        list.clear();
        List<Employee> resultList = em.createNamedQuery("Employee.findAll", Employee.class).getResultList();
        for(Employee employee: resultList){
            list.add(employee);
        }
    }
    
    public List<Employee> getEmployees(){
        if(list.isEmpty()){
            init();
        }
        return list;
    }

    public void deleteAll(){
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Employee.deleteAll", Employee.class).executeUpdate();
            utx.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(IngredientBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void submit(){
        deleteAll();
        System.out.println(list.size());
        for(Employee item: list){
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
