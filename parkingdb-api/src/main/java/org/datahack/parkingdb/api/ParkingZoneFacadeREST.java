/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parkingdb.api;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.datahack.parkingdb.Bay;
import org.datahack.parkingdb.ParkingDBUtils;
import org.datahack.parkingdb.ParkingZone;

/**
 *
 * @author djabry
 */
@Stateless
@Path("parkingzone")
public class ParkingZoneFacadeREST extends AbstractFacade<ParkingZone> {
    //@PersistenceContext(unitName = "PARKING_PU")
    private EntityManager em= ParkingDBUtils.getEntityManager();

    public ParkingZoneFacadeREST() {
        super(ParkingZone.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(ParkingZone entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, ParkingZone entity) {
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
    public ParkingZone find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<ParkingZone> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<ParkingZone> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }
    
    @GET
    @Path("{lat1}/{lon1}/{lat2}/{lon2}")
    @Produces({"application/xml", "application/json"})
    public List<ParkingZone> findInBox(@PathParam("lat1") Double lat1, @PathParam("lon1") Double lon1,@PathParam("lat2") Double lat2,@PathParam("lon2") Double lon2) {

        String qString = "SELECT * FROM PARKINGZONE b WHERE b.latitude>="+lat1+" AND b.latitude<="+lat2+" AND b.longitude>="+lon1+" AND b.longitude<="+lon2;
        
        TypedQuery<ParkingZone> q = em.createQuery(qString,ParkingZone.class);
        
        List<ParkingZone> pZ = q.getResultList();
        
        return pZ;
        
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
