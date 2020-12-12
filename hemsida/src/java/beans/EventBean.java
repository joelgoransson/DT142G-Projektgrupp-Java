/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Event;
import java.io.IOException;
import java.io.InputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.Part;
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
    private Part file;
    private String savepath = System.getProperty("user.home") + "\\Documents\\Antons Skafferi\\";
    
     /**
     * Creates a new instance of EventBean
     */
    public EventBean() {
    }
    
    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
    public void createEvent(){
        newEvent = new Event();
    }

    public Event getNewEvent() {
        return newEvent;
    }
    
    private String processFile(){
        String fileName = Paths.get(getFile().getSubmittedFileName()).getFileName().toString();
        
        if(!Files.exists(Paths.get(savepath))){
            try {    
                Files.createDirectories(Paths.get(savepath));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        
        if(Files.exists(Paths.get(savepath + fileName))){
            int i = 0;
            while(Files.exists(Paths.get(savepath + i + fileName))){
                i++;
            }
            fileName = i + fileName;
        }
        
        try (InputStream input = file.getInputStream()) {
            Files.copy(input, Paths.get(savepath + fileName));
        }
        catch (IOException e) {
            e.printStackTrace();
            // Show faces message?
        }
        return Paths.get(savepath + fileName).toString();
    }
     
    public void submit(){
        newEvent.setId(findMaxID()+1);
        String filepath = processFile();
        System.out.println(filepath);
        newEvent.setImage(filepath);
        
        try {
            utx.begin();
            em.joinTransaction();
            em.persist(newEvent);
            utx.commit();   
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id){
        try {
            utx.begin();
            em.joinTransaction();
            em.createNamedQuery("Event.deleteById", Event.class).setParameter("id", id).executeUpdate();
            utx.commit();
        } catch (RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException | SystemException | NotSupportedException ex) {
            Logger.getLogger(LunchBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Integer findMaxID(){
        List<Event> resultList = em.createNamedQuery("Event.sortByID", Event.class).getResultList();
        if(resultList.size() <= 0){
            return 0;
        }
        return resultList.get(0).getId();
    }
    
    public List<Event> getEventsSortedByDate(){
        return em.createNamedQuery("Event.sortByDate", Event.class).getResultList();
    }

    public List<Event> getEvents(){
        return em.createNamedQuery("Event.findAll", Event.class).getResultList();
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
