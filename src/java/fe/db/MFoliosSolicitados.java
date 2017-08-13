/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Carlo García Sánchez
 */
@Entity
@Table(name = "FOLIOS_SOLICITADOS")
public class MFoliosSolicitados implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "Fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "Agente")
    private String agente;
    @Column(name = "Folios")
    private Integer folios;
    @Basic(optional = false)
    @Column(name = "licencias_ID")
    private Long licenciasID;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;

    public MFoliosSolicitados() {
    }

    public MFoliosSolicitados(String agente, int folios, Long licenciasID) {
        this.agente = agente;
        this.folios = folios;
        this.licenciasID = licenciasID;
        this.fecha = new Date();
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getAgente() {
        return agente;
    }

    public void setAgente(String agente) {
        this.agente = agente;
    }

    public Integer getFolios() {
        return folios;
    }

    public void setFolios(Integer folios) {
        this.folios = folios;
    }

    public Long getLicenciasID() {
        return licenciasID;
    }

    public void setLicenciasID(Long licenciasID) {
        this.licenciasID = licenciasID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof MFoliosSolicitados)) {
            return false;
        }
        MFoliosSolicitados other = (MFoliosSolicitados) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.FoliosSolicitados[ id=" + id + " ]";
    }
    
}
