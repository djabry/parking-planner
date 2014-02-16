/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.datahack.parking.api;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author djabry
 */
@Entity
@Table(name = "BAYEVENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bayevent.findAll", query = "SELECT b FROM Bayevent b"),
    @NamedQuery(name = "Bayevent.findById", query = "SELECT b FROM Bayevent b WHERE b.id = :id"),
    @NamedQuery(name = "Bayevent.findByEstimatedspaces", query = "SELECT b FROM Bayevent b WHERE b.estimatedspaces = :estimatedspaces"),
    @NamedQuery(name = "Bayevent.findByEventtime", query = "SELECT b FROM Bayevent b WHERE b.eventtime = :eventtime")})
public class Bayevent implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "ESTIMATEDSPACES")
    private Double estimatedspaces;
    @Column(name = "EVENTTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventtime;
    @JoinColumn(name = "BAY_ID", referencedColumnName = "ID")
    @ManyToOne
    private Bay bayId;

    public Bayevent() {
    }

    public Bayevent(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getEstimatedspaces() {
        return estimatedspaces;
    }

    public void setEstimatedspaces(Double estimatedspaces) {
        this.estimatedspaces = estimatedspaces;
    }

    public Date getEventtime() {
        return eventtime;
    }

    public void setEventtime(Date eventtime) {
        this.eventtime = eventtime;
    }

    public Bay getBayId() {
        return bayId;
    }

    public void setBayId(Bay bayId) {
        this.bayId = bayId;
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
        if (!(object instanceof Bayevent)) {
            return false;
        }
        Bayevent other = (Bayevent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.datahack.parking.api.Bayevent[ id=" + id + " ]";
    }
    
}
