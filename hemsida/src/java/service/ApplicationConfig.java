/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Joel
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.BillFacadeREST.class);
        resources.add(service.EmployeeFacadeREST.class);
        resources.add(service.EmployeepassesFacadeREST.class);
        resources.add(service.EventFacadeREST.class);
        resources.add(service.InformationFacadeREST.class);
        resources.add(service.IngredientFacadeREST.class);
        resources.add(service.LunchFacadeREST.class);
        resources.add(service.MenuitemFacadeREST.class);
        resources.add(service.MenuitemhasingredientFacadeREST.class);
        resources.add(service.OrdermenuitemFacadeREST.class);
        resources.add(service.PassFacadeREST.class);
        resources.add(service.TablesFacadeREST.class);
        resources.add(service.WeekdayFacadeREST.class);
    }
    
}
