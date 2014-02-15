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
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author djabry
 */

@Entity
@XmlRootElement
public class BayEvent implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL) 
    private Bay bay;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventTime;

    @Column
    private int numSPaces;
    
    public Bay getBay(){
        return bay;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param bay the bay to set
     */
    public void setBay(Bay bay) {
        this.bay = bay;
    }

    /**
     * @return the eventTime
     */
    public Date getEventTime() {
        return eventTime;
    }

    /**
     * @param eventTime the eventTime to set
     */
    public void setEventTime(Date eventTime) {
        this.eventTime = eventTime;
    }

    /**
     * @return the numSPaces
     */
    public int getNumSPaces() {
        return numSPaces;
    }

    /**
     * @param numSPaces the numSPaces to set
     */
    public void setNumSPaces(int numSPaces) {
        this.numSPaces = numSPaces;
    }
    
    
    
    
}
