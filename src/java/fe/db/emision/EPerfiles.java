/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.emision;

/**
 *
 * @author pedro
 */
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

@Entity
@Table(name = "E_PERFILES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EPerfiles.findAll", query = "SELECT m FROM EPerfiles m"),
    @NamedQuery(name = "EPerfiles.findAllCte", query = "SELECT m FROM EPerfiles m WHERE m.clientes = :clientes"),
    @NamedQuery(name = "EPerfiles.findAllId", query = "SELECT m FROM EPerfiles m WHERE m.id = :id")
})
public class EPerfiles implements Serializable {

    private static final long serialVersionUID = 1L;

    protected EPerfiles() {}

    public EPerfiles(String perfil, Long numero, Long padreId,EClientes clientes) {
        super();
        this.perfil = perfil;
        this.numero = numero;
        this.padreId = padreId;
        this.clientes = clientes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PERFIL")
    private String perfil;
    private Long numero;

    @Index(name = "PERFIL_PADRE_IDX")
    @Column(name = "PADRE_ID")
    private Long padreId;
    
    @ManyToOne
    private EClientes clientes;
    
    public Long getPadreId() {
        return padreId;
    }

    public void setPadreId(Long padreId) {
        this.padreId = padreId;
    }

    public EClientes getClientes() {
        return clientes;
    }

    public void setClientes(EClientes clientes) {
        this.clientes = clientes;
    }

    public String getPerfil() {
        return perfil;
    }

    public Long getId() {
        return id;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
    
    @Override
    public String toString() {
        return perfil;
    }
}
