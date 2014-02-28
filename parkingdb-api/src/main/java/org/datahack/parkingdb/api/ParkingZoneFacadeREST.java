/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parkingdb.api;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import org.datahack.parkingdb.ParkingZone;
import org.datahack.parkingdb.ParkingZone_;

/**
 *
 * @author djabry
 */
@Stateless
@Path("parkingzone")
public class ParkingZoneFacadeREST extends AbstractFacade<ParkingZone> {
    @PersistenceContext(unitName = "PARKING_PU")
    private EntityManager em;//= ParkingDBUtils.getEntityManager();

    public ParkingZoneFacadeREST() {
        super(ParkingZone.class);
    }

//    @POST
//    @Override
//    @Consumes({"application/json", "application/xml"})
//    public void create(ParkingZone entity) {
//        super.create(entity);
//    }
//
//    @PUT
//    @Path("{id}")
//    @Consumes({"application/json", "application/xml"})
//    public void edit(@PathParam("id") Integer id, ParkingZone entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Integer id) {
//        super.remove(super.find(id));
//    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public ParkingZone find(@PathParam("id") Integer id) {
        return super.find(id);
    }


    @GET
    @Produces({"application/json"})
    public List<ParkingZone> find(
            @QueryParam("minLat") Double minLat,
            @QueryParam("minLon") Double minLon,
            @QueryParam("maxLat") Double maxLat,
            @QueryParam("maxLon") Double maxLon) {

        
        CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();

        CriteriaQuery<ParkingZone> q = cb.createQuery(ParkingZone.class);
        Root<ParkingZone> pz = q.from(ParkingZone.class);

        List<Predicate> predicates = new ArrayList();

        
        
        if(minLat!=null){
            predicates.add(cb.ge(pz.get(ParkingZone_.latitude), minLat));
        }
        
        if(minLon!=null){
            predicates.add(cb.ge(pz.get(ParkingZone_.longitude), minLon));
        }
        
        if(maxLat!=null){
            predicates.add(cb.le(pz.get(ParkingZone_.latitude), maxLat));
        }
        
        if(maxLon!=null){
            predicates.add(cb.le(pz.get(ParkingZone_.longitude), maxLon));
        }
        
        Predicate[] arr = Iterables.toArray(predicates, Predicate.class);
        q= q.where(arr);
        
        TypedQuery<ParkingZone> pzQuery = em.createQuery(q);
        
        return pzQuery.getResultList();

        
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<ParkingZone> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    
}
