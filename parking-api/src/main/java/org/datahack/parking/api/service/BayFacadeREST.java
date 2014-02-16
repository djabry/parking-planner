/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parking.api.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.datahack.parking.api.Bay;

/**
 *
 * @author djabry
 */
@Stateless
@Path("bay")
public class BayFacadeREST extends AbstractFacade<Bay> {
    @PersistenceContext(unitName = "org.datahack_parking-api_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    public BayFacadeREST() {
        super(Bay.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Bay entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Bay entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Bay find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/json"})
    public List<Bay> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Bay> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
        @GET
    @Path("{lat1}/{lon1}/{lat2}/{lon2}")
    @Produces({"application/xml", "application/json"})
    public List<Bay> findInBox(@PathParam("lat1") Double lat1, @PathParam("lon1") Double lon1,@PathParam("lat2") Double lat2,@PathParam("lon2") Double lon2) {

        String qString = "SELECT * FROM BAY b WHERE b.latitude>="+lat1+" AND b.latitude<="+lat2+" AND b.longitude>="+lon1+" AND b.longitude<="+lon2;
        
        Query q = em.createNativeQuery(qString,Bay.class);
        
        List<Bay> bays = q.getResultList();
        
        return bays;
        
    }
    
            @GET
    @Path("testfindinbox")
    @Produces({"application/xml", "application/json"})
    public List<Bay> testCase() {

        //return this.findAll();
        return this.findInBox(-5.0,-10.0, 5.0, 60.0);
        
    }
    
}
