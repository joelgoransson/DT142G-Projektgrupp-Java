/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Pass;
import java.util.List;
import java.util.Objects;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author joaki
 */
@Stateless
@Path("entities.pass")
public class PassFacadeREST extends AbstractFacade<Pass> {

    @PersistenceContext(unitName = "HemsidaPU")
    private EntityManager em;

    public PassFacadeREST() {
        super(Pass.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Pass entity) {
        super.create(entity);
    }
    
    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    public Pass createAutoId(Pass entity){
        int id = 0;
        for(Pass i : super.findAll()){
            if(i.getId() > id){
                id = i.getId();
            }
        }
        entity.setId(id+1);
        super.create(entity);
        return entity;
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Pass entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Pass find(@PathParam("id") Integer id) {
        return super.find(id);
    }
    
    @GET
    @Path("Pass/{passnr}/Day/{day}/Week/{week}")
    @Produces(MediaType.APPLICATION_XML)
    public Pass findSpecial(@PathParam("passnr") Integer passnr, @PathParam("day") Integer day, @PathParam("week") Integer week){
        for(Pass i : super.findAll()){
            if(Objects.equals(i.getPass(), passnr) && Objects.equals(i.getWeekday(), day) && Objects.equals(i.getWeeknr(), week)){
                return i;
            }
        }
        return null;
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pass> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Pass> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
