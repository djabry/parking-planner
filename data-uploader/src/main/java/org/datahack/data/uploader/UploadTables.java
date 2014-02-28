/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.datahack.data.uploader;

import au.com.bytecode.opencsv.CSVReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.apache.commons.io.FileUtils;
import org.datahack.parkingdb.Bay;
import org.datahack.parkingdb.BayEvent;
import org.datahack.parkingdb.ParkingStreet;
import org.datahack.parkingdb.ParkingZone;
import org.datahack.parkingdb.connector.ParkingDBConnector;

/**
 *
 * @author djabry
 */
public class UploadTables {

    public static void main(String[] args) {

        
        EntityManager em = ParkingDBConnector.getEntityManager();
        
        
        
        try {
            File output = File.createTempFile("WCC_ParkingStreets", "csv");
            output.deleteOnExit();
            String csvName = "WCC_ParkingStreets.csv";
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
                        
                        
                        ParkingZone pZ = em.find(ParkingZone.class,parkingZoneKey );
                        
                        if(pZ==null){
                            pZ = new ParkingZone();
                            pZ.setId(parkingZoneKey);
                            
                        }

                        pS.setParkingZone(pZ);
                        em.merge(pS);
                        
                            
                        }catch(NumberFormatException e){
                            System.out.println("Failed to merge row "+row);
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
        
        //Uplad Bay data
        String csvName = "bay_table.csv";
        
        try {
            File output = File.createTempFile("bay_table", "csv");
            output.deleteOnExit();
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
                        
                        
                        
                        
                        ParkingStreet find = em.find(ParkingStreet.class, parkingStreetKey);
                        
                        if(find!=null){
                                ParkingZone pZ = find.getParkingZone();
                                
                                if(pZ!=null){
                                    Set<Bay> bays = pZ.getBays();
                                    
                                    if(bays==null){
                                        bays = new LinkedHashSet();
                                        pZ.setBays(bays);
                                    }
                                    
                                    bays.add(b);
                                    b.setParkingZone(pZ);
                                    
                                    //Calculate parking zone location as mean of lats and lons
                                    Iterator<Bay> iterator = bays.iterator();
                                    Integer numBays  = bays.size();
                                    
                                   
                                    Double latitude = 0.0;
                                    Double longitude = 0.0;

                                    while(iterator.hasNext()){
                                        Bay next = iterator.next();
                                        latitude+=next.getLatitude()/numBays;
                                        longitude+=next.getLongitude()/numBays;
 
                                    }
                                    
                                    pZ.setLatitude(latitude);
                                    pZ.setLongitude(longitude);
                                }
                                
                                em.merge(pZ);
                                
                                System.out.println("Bay added to zone");
                        }
                        
                        
                        
                        em.merge(b);
                        
                            
                        }catch(NumberFormatException e){
                            System.out.println("Failed to merge row "+row);
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
            File output = File.createTempFile("bay_event_table", "csv");
            output.deleteOnExit();
            csvName = "bay_event_table.csv";
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
                        
                        BayEvent bE = new BayEvent();
                        
                        try{
                            
                        Integer id = Integer.parseInt(str[0]);
                        Integer bayId = Integer.parseInt(str[1]);
                        String eventTimeString = str[2];
                        Double estimatedSpaces = Double.parseDouble(str[3]);
                        
                        bE.setId(id);
                        
                        Bay find = em.find(Bay.class, bayId);
                        bE.setBay(find);
                        
                        
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
                        java.util.Date d;

                        d = df.parse(eventTimeString);

                        bE.setEventTime(d);
                        
                        bE.setEstimatedSpaces(estimatedSpaces);
                        
                        em.merge(bE);
                          
                        }catch(NumberFormatException e){
                            System.out.println("Failed to merge row "+row);
                        } catch (ParseException ex) {
                            Logger.getLogger(UploadTables.class.getName()).log(Level.SEVERE, null, ex);
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
