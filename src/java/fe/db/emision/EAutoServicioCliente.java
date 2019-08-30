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

/**
 *
 * @author CarloGS
 * E_AUTO_SERVICIO_CTE
 */
@Entity
@Table(name = "E_AUTO_SERVICIO_CTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EAutoServicioCliente.findAll", query = "SELECT m FROM EAutoServicioCliente m"),
    @NamedQuery(name = "EAutoServicioCliente.findAllId", query = "SELECT m FROM EAutoServicioCliente m WHERE m.id = :id"),
    @NamedQuery(name = "EAutoServicioCliente.findAllRfc", query = "SELECT m FROM EAutoServicioCliente m WHERE m.rfc = :rfc"),
    @NamedQuery(name = "EAutoServicioCliente.findAllCte", query = "SELECT m FROM EAutoServicioCliente m WHERE m.clientes = :clientes")
})
public class EAutoServicioCliente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected EAutoServicioCliente() {}
    
    public EAutoServicioCliente(EAutoServicio autoServicio, EClientes clientes) {
        this.autoServicio = autoServicio;
        this.clientes = clientes;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "RFC", nullable = true, length = 13)
    private String rfc;

    @Column(name = "URL", nullable = true, length = 250)
    private String url;

    @Column(name = "EMAIL", nullable = true, length = 100)
    private String email;

    @Column(name = "TELEFONO", nullable = true, length = 50)
    private String telefono;
    
    @Column(name = "NOMBRE_URL", nullable = true, length = 100)
    private String nombreUrl;
    /**
     * Tiempo en que se puede facturar en días, predeterminado 30 días
     * apartir de la fecha de autofactura.
     */
    @Column(name = "VIGENCIA", nullable = false, length = 100)
    private int vigencia = 30;
    /**
     * Importe límite de facturación, -1 sin límite, 0 mismo día, 1 a N días
     * para facturar, > 2000 solo ese año de 4 dígitos.
     */
    @Column(name = "LIMITE", nullable = false, length = 100)
    private double limite = -1d;
    
    @ManyToOne
//    @JoinColumn(name="autoServicio_ID")
    private EAutoServicio autoServicio;
    
    @ManyToOne
    private ERegimen regimen;
    
    @ManyToOne
//    @JoinColumn(name="clientes_ID")
    private EClientes clientes;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreUrl() {
        return nombreUrl;
    }

    public void setNombreUrl(String nombreUrl) {
        this.nombreUrl = nombreUrl;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    /**
     * @return the autoServicio
     */
    public EAutoServicio getAutoServicio() {
        return autoServicio;
    }

    /**
     * @param autoServicio the autoServicio to set
     */
    public void setAutoServicio(EAutoServicio autoServicio) {
        this.autoServicio = autoServicio;
    }

    /**
     * @return the regimen
     */
    public ERegimen getRegimen() {
        return regimen;
    }

    /**
     * @param regimen the regimen to set
     */
    public void setRegimen(ERegimen regimen) {
        this.regimen = regimen;
    }

    /**
     * @return the clientes
     */
    public EClientes getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(EClientes clientes) {
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
        if (!(object instanceof EAutoServicioCliente)) {
            return false;
        }
        EAutoServicioCliente other = (EAutoServicioCliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombreUrl;
    }    
}
