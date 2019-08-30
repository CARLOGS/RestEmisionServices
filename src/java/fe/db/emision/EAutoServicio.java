/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.emision;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CarloGS
 * Tabla E_AUTO_SERVICIO
 */
@Entity
@Table(name = "E_AUTO_SERVICIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EAutoServicio.findAll", query = "SELECT m FROM EAutoServicio m"),
    @NamedQuery(name = "EAutoServicio.findAllDesc", query = "SELECT m FROM EAutoServicio m WHERE m.descripcion = :descripcion"),
    @NamedQuery(name = "EAutoServicio.findAllId", query = "SELECT m FROM EAutoServicio m WHERE m.id = :id"),
})
public class EAutoServicio implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected EAutoServicio() {}

    public EAutoServicio(String descripcion) {
        this.descripcion = descripcion;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "DESCRIPCION", nullable = false, length = 150)
    private String descripcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof EAutoServicio)) {
            return false;
        }
        EAutoServicio other = (EAutoServicio) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.emision.EAutoServicio[ id=" + id + " ]";
    }
    
}
