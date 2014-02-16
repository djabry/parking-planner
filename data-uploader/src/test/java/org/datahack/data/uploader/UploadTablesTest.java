/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.data.uploader;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class UploadTablesTest {
    
    public UploadTablesTest() {
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
     * Test of main method, of class UploadTables.
     */
    @org.junit.Test
    public void testMain() {
        System.out.println("main");
              DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                        java.util.Date d;
                String eventTimeString = "2013-12-20 16:00:00";
        try {
            d = df.parse(eventTimeString);
            System.out.println("Parsed date = "+d.toString());
        } catch (ParseException ex) {
            Logger.getLogger(UploadTablesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
