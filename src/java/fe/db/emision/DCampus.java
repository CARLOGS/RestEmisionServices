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
import javax.persistence.ManyToOne;
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
@Table(name = "D_CAMPUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DCampus.findAll", query = "SELECT m FROM DCampus m"),
    @NamedQuery(name = "DCampus.findAllN", query = "SELECT m FROM DCampus m WHERE m.clientes = :clientes"),
    @NamedQuery(name = "DCampus.findAllId", query = "SELECT m FROM DCampus m WHERE m.id = :id")
})
public class DCampus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Index(name = "EMITIDO_NOM_IDX")
    @Column(name = "NOMBRE", nullable = false, length = 150)
    private String nombre;
    @Column(name = "CALLE", nullable = false, length = 200)
    private String calle;
    @Column(name = "NOEXT", nullable = true, length = 50)
    private String noExt;
    @Column(name = "NOINT", nullable = true, length = 20)
    private String noInt;
    @Column(name = "COLONIA", nullable = true, length = 20)
    private String colonia;
    @Column(name = "LOCALIDAD", nullable = true, length = 100)
    private String localidad;
    @Column(name = "MUNICIPIO", nullable = true, length = 100)
    private String municipio;
    @Column(name = "ESTADO", nullable = true, length = 100)
    private String estado;
    @Column(name = "CP", nullable = true, length = 5)
    private String cp;
    @Column(name = "PAIS", nullable = true, length = 50)
    private String pais;
    @Column(name = "GLN", nullable = true, length = 20)
    private String gln;
    @ManyToOne
    private DInstitucion clientes;

    protected DCampus() {}

    public DCampus(String nombre, String calle, String cp, String pais, DInstitucion clientes) {
        this.nombre = nombre;
        this.calle = calle;
        this.cp = cp;
        this.pais = pais;
        this.clientes = clientes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNoExt() {
        return noExt;
    }

    public void setNoExt(String noExt) {
        this.noExt = noExt;
    }

    public String getNoInt() {
        return noInt;
    }

    public void setNoInt(String noInt) {
        this.noInt = noInt;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGln() {
        return gln;
    }

    public void setGln(String gln) {
        this.gln = gln;
    }

    public DInstitucion getClientes() {
        return clientes;
    }

    public void setClientes(DInstitucion clientes) {
        this.clientes = clientes;
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
        if (!(object instanceof DCampus)) {
            return false;
        }
        DCampus other = (DCampus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
}
