/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.retenciones;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CarloGS
 */
@Entity
@Table(name = "CR_DIV_O_UTIL", indexes = {
    @Index(columnList = "CLAVE", unique = true)
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CRDivUtil.findAll", query = "SELECT m FROM CRDivUtil m"),
    @NamedQuery(name = "CRDivUtil.findAllCve", query = "SELECT m FROM CRDivUtil m WHERE m.clave = :clave")
})
public class CRDivUtil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CLAVE", nullable = false, length = 3)
    private String clave;
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof CRDivUtil)) {
            return false;
        }
        CRDivUtil other = (CRDivUtil) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return clave + " - " + descripcion;
    }
    
}
