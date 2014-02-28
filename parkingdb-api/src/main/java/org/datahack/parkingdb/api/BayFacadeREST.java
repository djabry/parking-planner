/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.datahack.parkingdb.api;

import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
import org.datahack.parkingdb.Bay;
import org.datahack.parkingdb.Bay_;
import org.datahack.parkingdb.ParkingZone;
import org.datahack.parkingdb.ParkingZone_;

/**
 *
 * @author djabry
 */
@Stateless
@Path("bay")
public class BayFacadeREST extends AbstractFacade<Bay> {

    @PersistenceContext(unitName = "PARKING_PU")
    private EntityManager em;//=ParkingDBUtils.getEntityManager();

    public BayFacadeREST() {
        super(Bay.class);
    }

//    @POST
//    @Override
//    @Consumes({"application/json", "application/xml"})
//    public void create(Bay entity) {
//        super.create(entity);
//    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/json", "application/xml"})
//    public void edit(@PathParam("id") Integer id, Bay entity) {
//        super.edit(entity);
//    }

//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Integer id) {
//        super.remove(super.find(id));
//    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Bay find(@PathParam("id") Integer id) {
        return super.find(id);
    }

    @GET
    @Produces({"application/json"})
    public List<Bay> find(
            @QueryParam("parkingZoneId") Integer pId, 
            @QueryParam("totalSpaces") Integer totalSpaces,
            @QueryParam("minLat") Double minLat,
            @QueryParam("minLon") Double minLon,
            @QueryParam("maxLat") Double maxLat,
            @QueryParam("maxLon") Double maxLon) {

        
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Bay> q = cb.createQuery(Bay.class);
        Root<Bay> bay = q.from(Bay.class);

        List<Predicate> predicates = new ArrayList();
        if(pId!=null){
            predicates.add(cb.equal((bay.get(Bay_.parkingZone)).get(ParkingZone_.id), pId));
        }

        if(totalSpaces!=null){
            predicates.add(cb.equal((bay.get(Bay_.totalSpaces)), totalSpaces));
        }
        
        
        if(minLat!=null){
            predicates.add(cb.ge(bay.get(Bay_.latitude), minLat));
        }
        
        if(minLon!=null){
            predicates.add(cb.ge(bay.get(Bay_.longitude), minLon));
        }
        
        if(maxLat!=null){
            predicates.add(cb.le(bay.get(Bay_.latitude), maxLat));
        }
        
        if(maxLon!=null){
            predicates.add(cb.le(bay.get(Bay_.longitude), maxLon));
        }
        
        Predicate[] arr = Iterables.toArray(predicates, Predicate.class);
        q= q.where(arr);
        TypedQuery<Bay> bayQuery = em.createQuery(q);
        List<Bay> resultList = bayQuery.getResultList();
        
        return resultList;

        
    }


    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
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

   

}
