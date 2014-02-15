/*
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
import javax.persistence.JoinColumn;
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
    private int id;
    
    @Column
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    private Zone zone;
    @Column
    private double longitude;
    @Column
    private double latitude;
    @Column
    private int totalSpaces;
    
    public Zone getZone(){
        return zone; 
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
    
    
    
}
