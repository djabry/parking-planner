/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.datahack.data.uploader;

import au.com.bytecode.opencsv.CSVParser;
import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.io.FileUtils;
import org.datahack.parkingdb.Bay;
import org.datahack.parkingdb.ParkingStreet;

/**
 *
 * @author djabry
 */
public class UploadTables {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PARKING_PU");
        EntityManager em = emf.createEntityManager();

        
        //Uplad Bay data
        String csvName = "bay_table.csv";

        try {
            File output = File.createTempFile("bay_table", "csv");
            URL resource = UploadTables.class.getResource("/"+csvName);
            FileUtils.copyURLToFile(resource, output);
            CSVReader reader = new CSVReader(new FileReader(output.getPath()));
            
            //Headings
            String[] str = reader.readNext();
            em.getTransaction().begin();
            int row = 0;
            while(str!=null){
                row++;
                str= reader.readNext();
                if(str!=null){
                    
                    if(str.length==5){
                        
                        Bay b = new Bay();
                        try{
                            
                            Integer id = Integer.parseInt(str[0]);
                        Double lat = Double.parseDouble(str[1]);
                        Double lon = Double.parseDouble(str[2]);
                        Integer spaces = Integer.parseInt(str[3]);
                        Integer parkingStreetKey = Integer.parseInt(str[4]);
                        
                        
                        b.setId(id);
                        b.setLatitude(lat);
                        b.setLongitude(lon);
                        b.setTotalSpaces(spaces);
                        
                        
                        
                        em.persist(b);
                            
                        }catch(NumberFormatException e){
                            System.out.println("Failed to persist row "+row);
                        }
                        
                        if(row%1000==0){
                            em.getTransaction().commit();
                            em.clear();
                            em.getTransaction().begin();
                        }
                        
                    
                    }
                    
                }
                
            }
            
            em.getTransaction().commit();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadTables.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadTables.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            File output = File.createTempFile("WCC_ParkingStreets", "csv");
            csvName = "WCC_ParkingStreets.csv";
            URL resource = UploadTables.class.getResource("/"+csvName);
            FileUtils.copyURLToFile(resource, output);
            CSVReader reader = new CSVReader(new FileReader(output.getPath()));
            
            //Headings
            String[] str = reader.readNext();
            em.getTransaction().begin();
            int row = 0;
            while(str!=null){
                row++;
                str= reader.readNext();
                if(str!=null){
                    
                    if(str.length==4){
                        
                        ParkingStreet pS = new ParkingStreet();
                        
                        try{
                            
                        Integer id = Integer.parseInt(str[0]);
                        Integer usrn = Integer.parseInt(str[1]);
                        String streetName = str[2];
                        Integer parkingZoneKey = Integer.parseInt(str[3]);
                        pS.setId(id);
                        pS.setStreetName(streetName);
                        pS.setuSRN(usrn);

                        em.persist(pS);
                            
                        }catch(NumberFormatException e){
                            System.out.println("Failed to persist row "+row);
                        }
                        
                        if(row%1000==0){
                            em.getTransaction().commit();
                            em.clear();
                            em.getTransaction().begin();
                        }
                        
                        
                    
                    }
                    
                }
                
            }
            
            em.getTransaction().commit();
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UploadTables.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UploadTables.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        

    }

}
