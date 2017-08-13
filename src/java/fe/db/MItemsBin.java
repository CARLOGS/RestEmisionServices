/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author CARLO
 */
@Entity
@Table(name="ITEMS_BIN")
public class MItemsBin implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(name="OBJETO", nullable=false)
    private byte[] objeto;
    
    @ManyToOne
    private MItems items;

    protected MItemsBin() {}

    // Constructor inicial
    public MItemsBin(byte[] objeto, MItems items) {
            this.objeto = objeto;
            this.items = items;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getObjeto() {
        return objeto;
    }

    public void setObjeto(byte[] objeto) {
        this.objeto = objeto;
    }

    public MItems getItems() {
        return items;
    }

    public void setItems(MItems items) {
        this.items = items;
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
        if (!(object instanceof MItemsBin)) {
            return false;
        }
        MItemsBin other = (MItemsBin) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.emision.MItemsBin[ id=" + id + " ]";
    }
    
}
