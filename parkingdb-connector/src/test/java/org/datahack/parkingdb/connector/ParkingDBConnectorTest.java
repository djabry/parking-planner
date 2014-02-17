/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parkingdb.connector;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import org.datahack.parkingdb.Bay;
import org.datahack.parkingdb.BayEvent;
import org.datahack.parkingdb.ParkingZone;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author djabry
 */
public class ParkingDBConnectorTest {
    
    public ParkingDBConnectorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getEntityManager method, of class ParkingDBConnector.
     */
    @Test
    public void testGetEntityManager() {
        System.out.println("getEntityManager");
        //EntityManager expResult = null;
        EntityManager em = ParkingDBConnector.getEntityManager();
        Bay testBay  = new Bay();
         testBay.setId(Integer.MAX_VALUE);

         testBay.setLatitude(51.0);
         testBay.setLongitude(0.0);
         Set<Bay> bays = new LinkedHashSet<Bay>();
         bays.add(testBay);
         
         ParkingZone z = new ParkingZone();
         z.setId(Integer.MAX_VALUE);
         z.setBays(bays);
         
         z.setLatitude(51.0);
         z.setLongitude(0.0);
         
         
         
         testBay.setParkingZone(z);
         BayEvent bE = new BayEvent();
         bE.setId(Long.MAX_VALUE);
         bE.setEventTime(Calendar.getInstance().getTime());
         bE.setBay(testBay);
         
         em.getTransaction().begin();
         em.persist(z);
         em.persist(bE);
         em.getTransaction().commit();
         
        ParkingZone find = em.find(ParkingZone.class, z.getId());
         
        if(find!=null){
            System.out.println("Zone successfuly persisted");
            
        }
        
        BayEvent find1 = em.find(BayEvent.class, bE.getId());
        
        
        if(find1!=null){
            System.out.println("BayEvent successfuly persisted");
        }
       
        em.getTransaction().begin();
        
        em.remove(find);
        em.remove(find1);
        em.getTransaction().commit();
        
    }
    
}
