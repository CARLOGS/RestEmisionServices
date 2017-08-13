/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Carlo García Sánchez
 */
@Entity
@Table(name = "ITEMS_LICENCIAS")
public class MItemsLicencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "licencias_ID")
    private Long licenciasID;
    @Basic(optional = false)
    @Column(name = "items_ID")
    private int itemsID;
    @Basic(optional = false)
    @Column(name = "OPERACION")
    private String operacion;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    public MItemsLicencias() {
    }

    public MItemsLicencias(Integer id) {
        this.id = id;
    }

    public MItemsLicencias(Long licenciasID, int itemsID, String operacion) {
        this.licenciasID = licenciasID;
        this.itemsID = itemsID;
        this.operacion = operacion;
    }

    public Long getLicenciasID() {
        return licenciasID;
    }

    public void setLicenciasID(Long licenciasID) {
        this.licenciasID = licenciasID;
    }

    public int getItemsID() {
        return itemsID;
    }

    public void setItemsID(int itemsID) {
        this.itemsID = itemsID;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
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
        if (!(object instanceof MItemsLicencias)) {
            return false;
        }
        MItemsLicencias other = (MItemsLicencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.ItemsLicencias[ id=" + id + " ]";
    }
    
}
