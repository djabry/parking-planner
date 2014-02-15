/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parkingdb;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author djabry
 */
@Entity
@XmlRootElement
public class Zone implements Location, Serializable{
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    


    @OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Set<Bay> bays;
    
    @Column
    private double latitude;
    
    @Column
    private double longitude;

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
     * @return the bays
     */
    public Set<Bay> getBays() {
        return bays;
    }

    /**
     * @param bays the bays to set
     */
    public void setBays(Set<Bay> bays) {
        this.bays = bays;
    }

    /**
     * @return the latitude
     */
    @Override
    public double getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    @Override
    public double getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    
}
