/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.emision;

import fe.db.emision.catalogo.CNivel;
import fe.db.emision.catalogo.CTipoPeriodo;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CarloGS
 */
@Entity
@Table(name = "D_CARRERA", indexes = {
    @Index(columnList = "CLAVE", unique = true)
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DCarrera.findAll", query = "SELECT m FROM DCarrera m"),
    @NamedQuery(name = "DCarrera.findAllCve", query = "SELECT m FROM DCarrera m WHERE m.clave = :clave"),
    @NamedQuery(name = "DCarrera.findAllCliente", query = "SELECT m FROM DCarrera m WHERE m.clientes = :clientes")
})
public class DCarrera implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "CLAVE", nullable = false, length = 10)
    private String clave;
    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;
    @ManyToOne
    private CTipoPeriodo tipoPeriodo;
    @ManyToOne
    private CNivel nivel;
    @ManyToOne
    private DInstitucion clientes;

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

    public CTipoPeriodo getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(CTipoPeriodo tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }

    public CNivel getNivel() {
        return nivel;
    }

    public void setNivel(CNivel nivel) {
        this.nivel = nivel;
    }

    public DInstitucion getInstitucion() {
        return clientes;
    }

    public void setInstitucion(DInstitucion institucion) {
        this.clientes = institucion;
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
        if (!(object instanceof DCarrera)) {
            return false;
        }
        DCarrera other = (DCarrera) object;
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
