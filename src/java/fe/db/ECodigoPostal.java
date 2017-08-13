/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CarloGS
 */
@Entity
@Table(name = "E_CODIGO_POSTAL")
public class ECodigoPostal implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "D_CODIGO",length = 10,nullable = false)
    private String codigo;
    @Column(name = "D_ASENTA")
    private String asenta;
    @Column(name = "D_TIPO_ASENTA",length = 50)
    private String tipoAsenta;
    @Column(name = "D_MNPIO")
    private String municipio;
    @Column(name = "D_ESTADO",length = 100)
    private String estado;
    @Column(name = "D_CIUDAD")
    private String ciudad;
    @Column(name = "D_CP",length = 5)
    private String cp;
    @Column(name = "C_ESTADO",length = 5)
    private String cEstado;
    @Column(name = "C_OFICINA",length = 5)
    private String cOficina;
    @Column(name = "C_CP",length = 5)
    private String cCp;
    @Column(name = "C_TIPO_ASENTA",length = 5)
    private String cTipoAsenta;
    @Column(name = "C_MNPIO",length = 5)
    private String cMunicipio;
    @Column(name = "C_ASENTA_CPCONS",length = 5)
    private String cAsenta;
    @Column(name = "C_ZONA",length = 50)
    private String zona;
    @Column(name = "C_CVE_CIUDAD",length = 5)
    private String cCiudad;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAsenta() {
        return asenta;
    }

    public void setAsenta(String asenta) {
        this.asenta = asenta;
    }

    public String getTipoAsenta() {
        return tipoAsenta;
    }

    public void setTipoAsenta(String tipoAsenta) {
        this.tipoAsenta = tipoAsenta;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getcEstado() {
        return cEstado;
    }

    public void setcEstado(String cEstado) {
        this.cEstado = cEstado;
    }

    public String getcOficina() {
        return cOficina;
    }

    public void setcOficina(String cOficina) {
        this.cOficina = cOficina;
    }

    public String getcCp() {
        return cCp;
    }

    public void setcCp(String cCp) {
        this.cCp = cCp;
    }

    public String getcTipoAsenta() {
        return cTipoAsenta;
    }

    public void setcTipoAsenta(String cTipoAsenta) {
        this.cTipoAsenta = cTipoAsenta;
    }

    public String getcMunicipio() {
        return cMunicipio;
    }

    public void setcMunicipio(String cMunicipio) {
        this.cMunicipio = cMunicipio;
    }

    public String getcAsenta() {
        return cAsenta;
    }

    public void setcAsenta(String cAsenta) {
        this.cAsenta = cAsenta;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getcCiudad() {
        return cCiudad;
    }

    public void setcCiudad(String cCiudad) {
        this.cCiudad = cCiudad;
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
        if (!(object instanceof ECodigoPostal)) {
            return false;
        }
        ECodigoPostal other = (ECodigoPostal) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.ECodigoPostal[ id=" + id + " ]";
    }
    
}
