/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.pagos;

import fe.db.emision.ECfdi;
import fe.db.emision.EClientes;
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
 */
@Entity
@Table(name = "E_PAGOS_CFDI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EPagosCfdi.findAll", query = "SELECT m FROM EPagosCfdi m"),
    @NamedQuery(name = "EPagosCfdi.findAllNUuid", query = "SELECT m FROM EPagosCfdi m WHERE m.cfdi.uuid = :uuid AND m.clientes = :clientes"),
    @NamedQuery(name = "EPagosCfdi.findAllRelacionados", query = "SELECT m FROM EPagosCfdi m WHERE m.cfdi = :cfdi"),
    @NamedQuery(name = "EPagosCfdi.findAllN", query = "SELECT m FROM EPagosCfdi m WHERE m.id = :id")
})
public class EPagosCfdi implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "SERIE", nullable = true, length = 25)
    private String serie;

    @Column(name = "FOLIO", nullable = true, length = 40)
    private String folio;

    @Column(name = "MONEDA", nullable = false, length = 3)
    private String moneda = "MXN";

    @Column(name = "TIPO_CAMBIO", nullable = true)
    private Double tipoCambio;

    @Column(name = "METODO_PAGO", nullable = false, length = 3)
    private String metodoPago = "PUE";

    @Column(name = "PARCIALIDAD", nullable = true)
    private Integer parcialidad;

    @Column(name = "IMPORTE_PAGADO", nullable = false)
    private Double importePagado;

    @Column(name = "SALDO", nullable = false)
    private Double saldo;

    @ManyToOne
    private EPagos pagos;

    @ManyToOne
    private ECfdi cfdi;

    @ManyToOne
    private EClientes clientes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public Double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(Double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Integer getParcialidad() {
        return parcialidad;
    }

    public void setParcialidad(Integer parcialidad) {
        this.parcialidad = parcialidad;
    }

    public Double getImportePagado() {
        return importePagado;
    }

    public void setImportePagado(Double importePagado) {
        this.importePagado = importePagado;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public EPagos getPagos() {
        return pagos;
    }

    public void setPagos(EPagos pagos) {
        this.pagos = pagos;
    }

    public ECfdi getCfdi() {
        return cfdi;
    }

    public void setCfdi(ECfdi cfdi) {
        this.cfdi = cfdi;
    }

    public EClientes getClientes() {
        return clientes;
    }

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
        if (!(object instanceof EPagosCfdi)) {
            return false;
        }
        EPagosCfdi other = (EPagosCfdi) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.pagos.EPagosCfdi[ id=" + id + " ]";
    }
}
