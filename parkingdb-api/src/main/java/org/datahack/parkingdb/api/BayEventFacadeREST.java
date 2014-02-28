/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.datahack.parkingdb.api;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
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
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import org.datahack.parkingdb.Bay;
import org.datahack.parkingdb.BayEvent;
import org.datahack.parkingdb.BayEvent_;
import org.datahack.parkingdb.Bay_;

/**
 *
 * @author djabry
 */
@Stateless
@Path("bayevent")
public class BayEventFacadeREST extends AbstractFacade<BayEvent> {

    @PersistenceContext(unitName = "PARKING_PU")
    private EntityManager em; //=ParkingDBUtils.getEntityManager();

    public BayEventFacadeREST() {
        super(BayEvent.class);
    }

    @POST
    @Path("create")
    @Consumes({"application/json", "application/xml"})
    public BayEvent createNewBayEvent(
            @QueryParam("bayId") Integer bayId,
            @QueryParam("eventTime") String eventTimeString,
            @QueryParam("estimatedSpaces") Double estimatedSpaces) {

        BayEvent entity = new BayEvent();
        Date eventTime =null;
        if(eventTimeString!=null){
            eventTime = this.convertToDate(eventTimeString);
        }
        
        if (bayId != null && eventTime != null && estimatedSpaces != null) {

            Bay b = this.getEntityManager().find(Bay.class, bayId);

            if (b != null) {

                entity.setBay(b);

                entity.setEstimatedSpaces(estimatedSpaces);
                entity.setEventTime(eventTime);

                super.create(entity);
            }

        }

        return entity;
    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/json", "application/xml"})
//    public void edit(@PathParam("id") Long id, BayEvent entity) {
//        super.edit(entity);
//    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Long id) {
        super.remove(super.find(id));
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public BayEvent find(@PathParam("id") Long id) {
        return super.find(id);
    }

    @GET
    @Produces({"application/json"})
    public List<BayEvent> find(
            @QueryParam("bayId") Integer bayId,
            @QueryParam("minTime") String minTimeString,
            @QueryParam("maxTime") String maxTimeString) {

        if (bayId != null) {
            CriteriaBuilder cb = this.getEntityManager().getCriteriaBuilder();

            CriteriaQuery<BayEvent> q = cb.createQuery(BayEvent.class);
            Root<BayEvent> bE = q.from(BayEvent.class);

            List<Predicate> predicates = new ArrayList();

            predicates.add(cb.equal((bE.get(BayEvent_.bay)).get(Bay_.id), bayId));

            if (minTimeString != null) {
               

                    Date minTime = this.convertToDate(minTimeString);
                    
                    if(minTime!=null){
                        predicates.add(cb.greaterThanOrEqualTo(bE.get(BayEvent_.eventTime), minTime));
                    }
                    
                
                

            }

            if (maxTimeString != null) {
                
               
                    Date maxTime = this.convertToDate(maxTimeString);
                    
                    if(maxTime!=null){
                        predicates.add(cb.lessThanOrEqualTo(bE.get(BayEvent_.eventTime), maxTime));
                    }
                    

                
            }

            Predicate[] arr = Iterables.toArray(predicates, Predicate.class);
            q = q.where(arr);

            TypedQuery<BayEvent> pzQuery = em.createQuery(q);

            return pzQuery.getResultList();

        }
        return Lists.newArrayList();

    }

    class Prediction {

        private final Integer bayId;
        private final Date eventTime;
        private final Double value;
        private final Double error;

        public Prediction(Integer bayId, Date eventTime, Double value, Double error) {
            this.bayId = bayId;
            this.eventTime = eventTime;
            this.value = value;
            this.error = error;
        }

        /**
         * @return the bayId
         */
        public Integer getBayId() {
            return bayId;
        }

        /**
         * @return the eventTime
         */
        public Date getEventTime() {
            return eventTime;
        }

        /**
         * @return the value
         */
        public Double getValue() {
            return value;
        }

        public Double getError() {
            return error;
        }

    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/json"})
    public List<BayEvent> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
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
    @Path(value = "predict")
    @Produces(value = {"application/json"})
    public Prediction predict(
            @QueryParam(value = "bayId") Integer bayId, 
            @QueryParam(value = "eventTime") String eventTimeString) {
        
            Date eventTime = this.convertToDate(eventTimeString);
            Double prediction = 0.0;
            return new Prediction(bayId, eventTime, prediction, 100.0);

        
        
    }

   

}
