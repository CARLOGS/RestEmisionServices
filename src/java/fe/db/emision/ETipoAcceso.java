/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fe.db.emision;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author CARLO
 */
@Entity
@Table(name="E_TIPO_ACCESO")
public class ETipoAcceso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Contructor privado
    protected ETipoAcceso() {}
    
    public ETipoAcceso(String tipo) {
        this.tipo = tipo;
    }
    
    @Id
    @Column(name="ID", nullable=false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    @Column(name="TIPO", nullable=false, length=50)
    private String tipo = "EXTERNO";

    @Override 
    public int hashCode() { 
        int result = 1; 
        return result; 
    }

    @Override 
    public boolean equals(final Object obj) { 
        if (this == obj) { 
            return true; 
        } 
        if (obj == null) { 
            return false; 
        } 
        return true; 
    } 
}
