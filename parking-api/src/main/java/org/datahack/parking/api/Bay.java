/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parking.api;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author djabry
 */
@Entity
@Table(name = "BAY")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bay.findAll", query = "SELECT b FROM Bay b"),
    @NamedQuery(name = "Bay.findById", query = "SELECT b FROM Bay b WHERE b.id = :id"),
    @NamedQuery(name = "Bay.findByLatitude", query = "SELECT b FROM Bay b WHERE b.latitude = :latitude"),
    @NamedQuery(name = "Bay.findByLongitude", query = "SELECT b FROM Bay b WHERE b.longitude = :longitude"),
    @NamedQuery(name = "Bay.findByTotalspaces", query = "SELECT b FROM Bay b WHERE b.totalspaces = :totalspaces")})
public class Bay implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "LATITUDE")
    private Double latitude;
    @Column(name = "LONGITUDE")
    private Double longitude;
    @Column(name = "TOTALSPACES")
    private Integer totalspaces;
    @JoinTable(name = "PARKINGZONE_BAY", joinColumns = {
        @JoinColumn(name = "bays_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ParkingZone_ID", referencedColumnName = "ID")})
    @ManyToMany
    private Collection<Parkingzone> parkingzoneCollection;
    @JoinColumn(name = "PARKINGZONE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Parkingzone parkingzoneId;
    @OneToMany(mappedBy = "bayId")
    private Collection<Bayevent> bayeventCollection;

    public Bay() {
    }

    public Bay(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getTotalspaces() {
        return totalspaces;
    }

    public void setTotalspaces(Integer totalspaces) {
        this.totalspaces = totalspaces;
    }

    @XmlTransient
    public Collection<Parkingzone> getParkingzoneCollection() {
        return parkingzoneCollection;
    }

    public void setParkingzoneCollection(Collection<Parkingzone> parkingzoneCollection) {
        this.parkingzoneCollection = parkingzoneCollection;
    }

    public Parkingzone getParkingzoneId() {
        return parkingzoneId;
    }

    public void setParkingzoneId(Parkingzone parkingzoneId) {
        this.parkingzoneId = parkingzoneId;
    }

    @XmlTransient
    public Collection<Bayevent> getBayeventCollection() {
        return bayeventCollection;
    }

    public void setBayeventCollection(Collection<Bayevent> bayeventCollection) {
        this.bayeventCollection = bayeventCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bay)) {
            return false;
        }
        Bay other = (Bay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.datahack.parking.api.Bay[ id=" + id + " ]";
    }
    
    
    
}
