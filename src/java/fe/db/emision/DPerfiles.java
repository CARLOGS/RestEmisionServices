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
@Table(name = "D_PERFILES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DPerfiles.findAll", query = "SELECT m FROM DPerfiles m"),
    @NamedQuery(name = "DPerfiles.findAllCte", query = "SELECT m FROM DPerfiles m WHERE m.clientes = :clientes"),
    @NamedQuery(name = "DPerfiles.findAllId", query = "SELECT m FROM DPerfiles m WHERE m.id = :id")
})
public class DPerfiles implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    
    @Column(name = "PERFIL")
    private String perfil;
    @Column(name = "NUMERO")
    private Long numero;
    @Index(name = "PERFIL_PADRE_IDX")
    @Column(name = "PADRE_ID")
    private Long padreId;
    @ManyToOne
    private DCampus emitido;
    @ManyToOne
    private DInstitucion clientes;
    

    protected DPerfiles() {}

    public DPerfiles(String perfil, Long numero, Long padreId,DInstitucion clientes) {
        super();
        this.perfil = perfil;
        this.numero = numero;
        this.padreId = padreId;
        this.clientes = clientes;
    }

    public Long getPadreId() {
        return padreId;
    }

    public void setPadreId(Long padreId) {
        this.padreId = padreId;
    }

    public DInstitucion getClientes() {
        return clientes;
    }

    public void setClientes(DInstitucion clientes) {
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

    public DCampus getEmitido() {
        return emitido;
    }

    public void setEmitido(DCampus emitido) {
        this.emitido = emitido;
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
        if (!(object instanceof DPerfiles)) {
            return false;
        }
        DPerfiles other = (DPerfiles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return perfil;
    }
}
