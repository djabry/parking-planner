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
import javax.persistence.ManyToMany;
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
@Table(name = "PARKINGZONE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parkingzone.findAll", query = "SELECT p FROM Parkingzone p"),
    @NamedQuery(name = "Parkingzone.findById", query = "SELECT p FROM Parkingzone p WHERE p.id = :id"),
    @NamedQuery(name = "Parkingzone.findByLatitude", query = "SELECT p FROM Parkingzone p WHERE p.latitude = :latitude"),
    @NamedQuery(name = "Parkingzone.findByLongitude", query = "SELECT p FROM Parkingzone p WHERE p.longitude = :longitude")})
public class Parkingzone implements Serializable {
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
    @ManyToMany(mappedBy = "parkingzoneCollection")
    private Collection<Bay> bayCollection;
    @OneToMany(mappedBy = "parkingzoneId")
    private Collection<Bay> bayCollection1;
    @OneToMany(mappedBy = "parkingzoneId")
    private Collection<Parkingstreet> parkingstreetCollection;

    public Parkingzone() {
    }

    public Parkingzone(Integer id) {
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

    @XmlTransient
    public Collection<Bay> getBayCollection() {
        return bayCollection;
    }

    public void setBayCollection(Collection<Bay> bayCollection) {
        this.bayCollection = bayCollection;
    }

    @XmlTransient
    public Collection<Bay> getBayCollection1() {
        return bayCollection1;
    }

    public void setBayCollection1(Collection<Bay> bayCollection1) {
        this.bayCollection1 = bayCollection1;
    }

    @XmlTransient
    public Collection<Parkingstreet> getParkingstreetCollection() {
        return parkingstreetCollection;
    }

    public void setParkingstreetCollection(Collection<Parkingstreet> parkingstreetCollection) {
        this.parkingstreetCollection = parkingstreetCollection;
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
        if (!(object instanceof Parkingzone)) {
            return false;
        }
        Parkingzone other = (Parkingzone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.datahack.parking.api.Parkingzone[ id=" + id + " ]";
    }
    
}
