/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.pagos;

import fe.db.emision.EClientes;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author CarloGS
 */
@Entity
@Table(name = "E_PAGOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EPagos.findAll", query = "SELECT m FROM EPagos m"),
    @NamedQuery(name = "EPagos.findAllId", query = "SELECT m FROM EPagos m WHERE m.id = :id"),
    @NamedQuery(name = "EPagos.findNoOper", query = "SELECT m FROM EPagos m WHERE m.noOperacion = :noOperacion AND m.clientes = :clientes"),
    @NamedQuery(name = "EPagos.findAllNoOper", query = "SELECT m FROM EPagos m WHERE m.noOperacion = :noOperacion AND m.clientes = :clientes"),
    @NamedQuery(name = "EPagos.findAllN", query = "SELECT m FROM EPagos m WHERE m.clientes = :clientes AND m.fecha >= :fIni AND m.fecha <= :fFin ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagos.findAllNNombre", query = "SELECT m FROM EPagos m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagos.findAllNNombreRango", query = "SELECT m FROM EPagos m WHERE m.fecha > :fIni AND m.fecha <= :fFin AND m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagos.findAllNRfc", query = "SELECT m FROM EPagos m WHERE m.rfc = :rfc AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagos.findAllNRfcRango", query = "SELECT m FROM EPagos m WHERE m.fecha > :fIni AND m.fecha <= :fFin AND m.rfc = :rfc AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagos.findAllNFolio", query = "SELECT m FROM EPagos m WHERE m.folio = :folio AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagos.findAllNNoFactura", query = "SELECT m FROM EPagos m WHERE m.numeroFactura = :numeroFactura AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagos.findAllUuid", query = "SELECT m FROM EPagos m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "EPagos.findAllUuidFecha", query = "SELECT m FROM EPagos m WHERE m.uuid = :uuid AND m.fecha = :fecha"),
    @NamedQuery(name = "EPagos.findAllNUuid", query = "SELECT m FROM EPagos m WHERE m.uuid = :uuid AND m.clientes = :clientes"),
    @NamedQuery(name = "EPagos.findAllNEdoDoc", query = "SELECT m FROM EPagos m WHERE m.estadoDocumento = :estadoDocumento AND m.clientes = :clientes ORDER BY m.fecha DESC")
})
public class EPagos implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "FECHA_PAGO", nullable = false)
    private Date fechaPago = new Date();

    @Column(name = "MONTO", nullable = false)
    private Double monto;

    @Column(name = "FORMA_PAGO", nullable = false, length = 2)
    private String formaPago;

    @Column(name = "MONEDA", nullable = false, length = 3)
    private String moneda;

    @Column(name = "NO_OPERACION", nullable = true, length = 100)
    private String noOperacion;

    @Column(name = "RFC", nullable = true, length = 13)
    private String rfc;

    @Column(name = "NOMBRE", nullable = true, length = 250)
    private String nombre;

    @Column(name = "FECHA", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(name = "FOLIO", nullable = true, length = 20)
    private String folio;

    @Column(name = "NUMERO_FACTURA", nullable = true, length = 40)
    private String numeroFactura;

    @Column(name = "MODO", nullable = true)
    private Integer modo;
    
    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;
    
    @Column(name = "ESTADO_DOCUMENTO", nullable = false)
    private Integer estadoDocumento = 1;
    
    @Column(name = "SALDO", nullable = false)
    private Double saldo = 0d;
    
    @Column(name = "PARCIALIDADES", nullable = false)
    private Integer parcialidades = 1;

    @Column(name = "FECHA_CANCELACION", nullable = true)
    private Date fechaCancelacion;
    
    @Column(name = "MONGO_COLLECTION", nullable = true, length = 10)
    private String mongoCollection;

    @Column(name = "MONGO_IP", nullable = true, length = 100)
    private String mongoIP;

    @Column(name = "ESTATUS_CANCELA", nullable = true)
    private Short estatusCancela;

    @Column(name = "CANCELABLE", nullable = true)
    private Short cancelable;

    @Column(name = "OTRO1", nullable = true)
    private String otro1;

    @Column(name = "OTRO2", nullable = true)
    private String otro2;

    @Column(name = "OTRO3", nullable = true)
    private String otro3;

    @ManyToOne
    private EClientes clientes;
    
    @Column(name = "items_ID", nullable = true)
    private Long items;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getNoOperacion() {
        return noOperacion;
    }

    public void setNoOperacion(String noOperacion) {
        this.noOperacion = noOperacion;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Integer getModo() {
        return modo;
    }

    public void setModo(Integer modo) {
        this.modo = modo;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(Integer estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Integer getParcialidades() {
        return parcialidades;
    }

    public void setParcialidades(Integer parcialidades) {
        this.parcialidades = parcialidades;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Short getEstatusCancela() {
        return estatusCancela;
    }

    public void setEstatusCancela(Short estatusCancela) {
        this.estatusCancela = estatusCancela;
    }

    public Short getCancelable() {
        return cancelable;
    }

    public void setCancelable(Short cancelable) {
        this.cancelable = cancelable;
    }

    public String getOtro1() {
        return otro1;
    }

    public void setOtro1(String otro1) {
        this.otro1 = otro1;
    }

    public String getOtro2() {
        return otro2;
    }

    public void setOtro2(String otro2) {
        this.otro2 = otro2;
    }

    public String getOtro3() {
        return otro3;
    }

    public void setOtro3(String otro3) {
        this.otro3 = otro3;
    }

    /**
     * @return the mongoCollection
     */
    public String getMongoCollection() {
        return mongoCollection;
    }

    /**
     * @param mongoCollection the mongoCollection to set
     */
    public void setMongoCollection(String mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    /**
     * @return the mongoIP
     */
    public String getMongoIP() {
        return mongoIP;
    }

    /**
     * @param mongoIP the mongoIP to set
     */
    public void setMongoIP(String mongoIP) {
        this.mongoIP = mongoIP;
    }

    public EClientes getClientes() {
        return clientes;
    }

    public void setClientes(EClientes clientes) {
        this.clientes = clientes;
    }

    public Long getItems() {
        return items;
    }

    public void setItems(Long items) {
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
        if (!(object instanceof EPagos)) {
            return false;
        }
        EPagos other = (EPagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.pagos.EPagos[ id=" + id + " ]";
    }
}
