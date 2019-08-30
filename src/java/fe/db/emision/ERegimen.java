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
import org.hibernate.annotations.Index;

/**
 *
 * @author CarloGS
 */
@Entity
@Table(name = "E_REGIMEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ERegimen.findAll", query = "SELECT m FROM ERegimen m"),
    @NamedQuery(name = "ERegimen.findAllNInPersona", query = "SELECT m FROM ERegimen m WHERE m.persona IN :personas")
})
public class ERegimen implements Serializable {

    private static final long serialVersionUID = 1L;
    public static enum Persona {FISICA, MORAL, EXTRANJERO};
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Index(name = "REGIMEN_CLAVE_IDX")
    @Column(name = "CLAVE", nullable = false, length = 3)
    private String clave;
    @Column(name = "DESCRIPCION", nullable = false, length = 100)
    private String descripcion;
    @Column(name = "PERSONA", nullable = false)
    private int persona;

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

    public int getPersona() {
        return persona;
    }

    public void setPersona(int persona) {
        this.persona = persona;
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
        if (!(object instanceof ERegimen)) {
            return false;
        }
        ERegimen other = (ERegimen) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }
    
}
