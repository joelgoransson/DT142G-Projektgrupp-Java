/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kahre
 */
@Entity
@Table(name = "TABLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tables.findAll", query = "SELECT t FROM Tables t"),
    @NamedQuery(name = "Tables.findByTablenr", query = "SELECT t FROM Tables t WHERE t.tablenr = :tablenr")})
public class Tables implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TABLENR")
    private Integer tablenr;

    public Tables() {
    }

    public Tables(Integer tablenr) {
        this.tablenr = tablenr;
    }

    public Integer getTablenr() {
        return tablenr;
    }

    public void setTablenr(Integer tablenr) {
        this.tablenr = tablenr;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tablenr != null ? tablenr.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tables)) {
            return false;
        }
        Tables other = (Tables) object;
        if ((this.tablenr == null && other.tablenr != null) || (this.tablenr != null && !this.tablenr.equals(other.tablenr))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Tables[ tablenr=" + tablenr + " ]";
    }
    
}
