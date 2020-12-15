/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Employeepass;
import java.util.ArrayList;
import java.util.List;
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
@Path("entities.employeepass")
public class EmployeepassFacadeREST extends AbstractFacade<Employeepass> {

    @PersistenceContext(unitName = "HemsidaPU")
    private EntityManager em;

    public EmployeepassFacadeREST() {
        super(Employeepass.class);
    }

    @POST
    @Override
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void create(Employeepass entity) {
        super.create(entity);
    }
    
    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_XML)
    public void createNoId(Employeepass entity) {
        int id = 0;
        for(Employeepass i : super.findAll()){
            if(i.getId() > id){
                id = i.getId();
            }
        }
        entity.setId(id+1);
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void edit(@PathParam("id") Integer id, Employeepass entity) {
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
    public Employeepass find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Employeepass> findAll() {
        return super.findAll();
    }
    
    @GET
    @Path("employee/{employeename}")
    @Produces(MediaType.APPLICATION_XML)
    public List<Employeepass> findByName(@PathParam("employeename") String employeename) {
        List<Employeepass> ret = new ArrayList<>();
        for(Employeepass item : super.findAll()){
            if(item.getEmployeename().equals(employeename))
                ret.add(item);
        }
        return ret;
    }

    @GET
    @Path("{from}/{to}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Employeepass> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
