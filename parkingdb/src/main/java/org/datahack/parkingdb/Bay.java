/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parkingdb;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author djabry
 */
@Entity
@XmlRootElement
public class Bay implements Location, Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private ParkingZone parkingZone;
    @Column
    private double longitude;
    @Column
    private double latitude;
    @Column
    private int totalSpaces;
    
    public ParkingZone getParkingZone(){
        return parkingZone; 
    }

    @Override
    public double getLatitude() {
        return latitude; 
    }

    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the totalSpaces
     */
    public int getTotalSpaces() {
        return totalSpaces;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param parkingZone the zone to set
     */
    public void setParkingZone(ParkingZone parkingZone) {
        this.parkingZone = parkingZone;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param totalSpaces the totalSpaces to set
     */
    public void setTotalSpaces(int totalSpaces) {
        this.totalSpaces = totalSpaces;
    }
    
    
    
}
