/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parkingdb;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author djabry
 */
public class BayEventTest {
    @PersistenceContext(unitName = "PARKING_PU")
    private EntityManager em;
    
    public BayEventTest() {
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
     * Test of getBay method, of class BayEvent.
     */
    @Test
    @Ignore
    public void testGetBay() {
        System.out.println("getBay");
        BayEvent instance = new BayEvent();
        Bay expResult = null;
        Bay result = instance.getBay();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class BayEvent.
     */
    @Test
    @Ignore
    public void testGetId() {
        System.out.println("getId");
        BayEvent instance = new BayEvent();
        long expResult = 0L;
        long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class BayEvent.
     */
    @Test
    @Ignore
    public void testSetId() {
        System.out.println("setId");
        long id = 0L;
        BayEvent instance = new BayEvent();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBay method, of class BayEvent.
     */
    @Test
    @Ignore
    public void testSetBay() {
        System.out.println("setBay");
        Bay bay = null;
        BayEvent instance = new BayEvent();
        instance.setBay(bay);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventTime method, of class BayEvent.
     */
    @Test
    @Ignore
    public void testGetEventTime() {
        System.out.println("getEventTime");
        BayEvent instance = new BayEvent();
        Date expResult = null;
        Date result = instance.getEventTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEventTime method, of class BayEvent.
     */
    @Test
    @Ignore
    public void testSetEventTime() {
        System.out.println("setEventTime");
        Date eventTime = null;
        BayEvent instance = new BayEvent();
        instance.setEventTime(eventTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumSPaces method, of class BayEvent.
     */
    @Test
    @Ignore
    public void testGetNumSPaces() {
        System.out.println("getNumSPaces");
        BayEvent instance = new BayEvent();
        int expResult = 0;
        int result = instance.getNumSPaces();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumSPaces method, of class BayEvent.
     */
    @Test
    public void testSetNumSPaces() {
         System.out.println("setNumSPaces");
       
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PARKING_PU");
        em = emf.createEntityManager();
         Bay testBay  = new Bay();
         

         testBay.setLatitude(51.0);
         testBay.setLongitude(0.0);
         Set<Bay> bays = new LinkedHashSet<Bay>();
         bays.add(testBay);
         
         Zone z = new Zone();
         
         z.setBays(bays);
         
         z.setLatitude(51.0);
         z.setLongitude(0.0);
         
         
         
         testBay.setZone(z);
         BayEvent bE = new BayEvent();
         
         bE.setBay(testBay);
         
         em.getTransaction().begin();
         em.persist(z);
         em.persist(bE);
         em.getTransaction().commit();
         
        Zone find = em.find(Zone.class, z.getId());
         
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
