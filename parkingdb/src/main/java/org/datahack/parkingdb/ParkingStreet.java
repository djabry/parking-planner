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
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author djabry
 */
@Entity
public class ParkingStreet implements Serializable {
    
    @Id
    private int id;
    
    
    private int uSRN;
    
    private String streetName;
    
    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private ParkingZone parkingZone;

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
