/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parking.api;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author djabry
 */
@Entity
@Table(name = "PARKINGSTREET")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parkingstreet.findAll", query = "SELECT p FROM Parkingstreet p"),
    @NamedQuery(name = "Parkingstreet.findById", query = "SELECT p FROM Parkingstreet p WHERE p.id = :id"),
    @NamedQuery(name = "Parkingstreet.findByStreetname", query = "SELECT p FROM Parkingstreet p WHERE p.streetname = :streetname"),
    @NamedQuery(name = "Parkingstreet.findByUsrn", query = "SELECT p FROM Parkingstreet p WHERE p.usrn = :usrn")})
public class Parkingstreet implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Integer id;
    @Size(max = 255)
    @Column(name = "STREETNAME")
    private String streetname;
    @Column(name = "USRN")
    private Integer usrn;
    @JoinColumn(name = "PARKINGZONE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Parkingzone parkingzoneId;

    public Parkingstreet() {
    }

    public Parkingstreet(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStreetname() {
        return streetname;
    }

    public void setStreetname(String streetname) {
        this.streetname = streetname;
    }

    public Integer getUsrn() {
        return usrn;
    }

    public void setUsrn(Integer usrn) {
        this.usrn = usrn;
    }

    public Parkingzone getParkingzoneId() {
        return parkingzoneId;
    }

    public void setParkingzoneId(Parkingzone parkingzoneId) {
        this.parkingzoneId = parkingzoneId;
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
        if (!(object instanceof Parkingstreet)) {
            return false;
        }
        Parkingstreet other = (Parkingstreet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.datahack.parking.api.Parkingstreet[ id=" + id + " ]";
    }
    
}
