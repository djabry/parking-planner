/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parkingdb.connector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author djabry
 */
public class ParkingDBConnector {
    
    public static EntityManager getEntityManager(){
        EntityManagerFactory eF = Persistence.createEntityManagerFactory("PARKING_CONNECTOR_PU");
        return eF.createEntityManager();
    }
    
}
