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
 */

package org.datahack.parkingdb;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author djabry
 */
@Entity
public class ParkingStreet implements Serializable {
    
    @Id
    private int id;
    
    @Column
    private int uSRN;
    @Column
    private String streetName;
    
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private ParkingZone parkingZone;
    
     public int getParkingZoneId(){
        int pId = -1;
        ParkingZone pZ = this.getParkingZone();
        if(pZ!=null){
            pId = pZ.getId();
        }
        
        return pId;
    }
    
    public void setParkingZoneId(int id){
        
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the uSRN
     */
    public int getuSRN() {
        return uSRN;
    }

    /**
     * @param uSRN the uSRN to set
     */
    public void setuSRN(int uSRN) {
        this.uSRN = uSRN;
    }

    /**
     * @return the streetName
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @param streetName the streetName to set
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * @return the zone
     */
    @XmlTransient
    public ParkingZone getParkingZone() {
        return parkingZone;
    }

    /**
     * @param zone the zone to set
     */
    public void setParkingZone(ParkingZone zone) {
        this.parkingZone = zone;
    }

    /**
     * @return the parkingZone
     */
    
    
    
    
    
}
