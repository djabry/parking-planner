/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parking.api.service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author djabry
 */
@javax.ws.rs.ApplicationPath("resources")
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
        resources.add(org.datahack.parking.api.service.BayFacadeREST.class);
        resources.add(org.datahack.parking.api.service.BayeventFacadeREST.class);
        resources.add(org.datahack.parking.api.service.ParkingstreetFacadeREST.class);
        resources.add(org.datahack.parking.api.service.ParkingzoneFacadeREST.class);
        resources.add(org.datahack.parking.api.service.SequenceFacadeREST.class);
        resources.add(org.datahack.parking.api.service.TariffFacadeREST.class);
    }
    
}
