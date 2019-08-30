/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.emision;

import java.io.Serializable;
import java.util.Date;
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
 */
/**
 *
 * @author CarloGS
 */
@Entity
@Table(name = "E_AUTO_CLIENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EAutoClientes.findAll", query = "SELECT m FROM EAutoClientes m"),
    @NamedQuery(name = "EAutoClientes.findAllRef", query = "SELECT m FROM EAutoClientes m WHERE m.rfc = :rfc")
})
public class EAutoClientes implements Serializable {

    private static final long serialVersionUID = 1L;

    protected EAutoClientes() {}

    public EAutoClientes(String rfc) {
        this.rfc = rfc;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "RFC", nullable = false, length = 13)
    private String rfc;

    @Column(name = "EMAIL", nullable = true, length = 50)
    private String correo;

    @Column(name = "NOMBRE", nullable = false, length = 255)
    private String nombre;

    @Column(name = "DIRECCION", nullable = true, length = 500)
    private String calle;

    @Column(name = "ESTADO", nullable = true, length = 100)
    private String estado;

    @Column(name = "MUNICIPIO", nullable = true, length = 100)
    private String municipio;

    @Column(name = "COLONIA", nullable = true, length = 200)
    private String colonia;

    @Column(name = "CODIGO_POSTAL", nullable = true, length = 10)
    private String cp;

    @Column(name = "FECHA", nullable = false)
    private Date fecha = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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
        if (!(object instanceof EAutoClientes)) {
            return false;
        }
        EAutoClientes other = (EAutoClientes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.emision.EAutoClientes[ id=" + id + " ]";
    }
    
}
