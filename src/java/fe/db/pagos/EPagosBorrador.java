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
import javax.persistence.Index;
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
@Table(name = "E_PAGOS_BORRADOR", indexes = {
    @Index(name = "PAG_FECHA_BRR_IDX", columnList = "FECHA_PAGO"),
    @Index(name = "PAG_NUMOPER_BRR_IDX", columnList = "NO_OPERACION")
})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EPagosBorrador.findAll", query = "SELECT m FROM EPagosBorrador m"),
    @NamedQuery(name = "EPagosBorrador.findAllId", query = "SELECT m FROM EPagosBorrador m WHERE m.id = :id"),
    @NamedQuery(name = "EPagosBorrador.findNoOper", query = "SELECT m FROM EPagosBorrador m WHERE m.noOperacion = :noOperacion AND m.clientes = :clientes"),
    @NamedQuery(name = "EPagosBorrador.findAllNoOper", query = "SELECT m FROM EPagosBorrador m WHERE m.noOperacion = :noOperacion AND m.clientes = :clientes"),
    @NamedQuery(name = "EPagosBorrador.findAllN",  query = "SELECT m FROM EPagosBorrador m WHERE m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllN2", query = "SELECT m FROM EPagosBorrador m WHERE m.clientes = :clientes AND YEAR(m.fecha) = :anio AND MONTH(m.fecha) = :mes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllN3", query = "SELECT m FROM EPagosBorrador m WHERE m.clientes = :clientes AND YEAR(m.fecha) = :anio ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllNNombre", query = "SELECT m FROM EPagosBorrador m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllNNombreRango", query = "SELECT m FROM EPagosBorrador m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllUuid", query = "SELECT m FROM EPagosBorrador m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "EPagosBorrador.findAllUuidFecha", query = "SELECT m FROM EPagosBorrador m WHERE m.uuid = :uuid AND m.fecha = :fecha"),
    @NamedQuery(name = "EPagosBorrador.findAllNUuid", query = "SELECT m FROM EPagosBorrador m WHERE m.uuid = :uuid AND m.clientes = :clientes"),
    @NamedQuery(name = "EPagosBorrador.findAllNRfc", query = "SELECT m FROM EPagosBorrador m WHERE m.rfc = :rfc AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllNRfcRango", query = "SELECT m FROM EPagosBorrador m WHERE m.rfc = :rfc AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllNFolioErp", query = "SELECT m FROM EPagosBorrador m WHERE m.folioErp = :folioErp AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllNOtro1", query = "SELECT m FROM EPagosBorrador m WHERE m.otro1 = :otro1 AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllNOtro2", query = "SELECT m FROM EPagosBorrador m WHERE m.otro2 = :otro2 AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllNOtro3", query = "SELECT m FROM EPagosBorrador m WHERE m.otro3 = :otro3 AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "EPagosBorrador.findAllNEdoDoc", query = "SELECT m FROM EPagosBorrador m WHERE m.estadoDocumento = :estadoDocumento AND m.clientes = :clientes ORDER BY m.fecha DESC")
})
public class EPagosBorrador implements Serializable {

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

    @Column(name = "FOLIO_ERP", nullable = true, length = 20)
    private String folioErp;
    
    @Column(name = "ESTADO_DOCUMENTO", nullable = false)
    private Integer estadoDocumento = 1;

    @Column(name = "SALDO", nullable = false)
    private Double saldo = 0d;
    
    @Column(name = "PARCIALIDADES", nullable = false)
    private Integer parcialidades = 1;

    @Column(name = "MONGO_COLLECTION", nullable = true, length = 10)
    private String mongoCollection;

    @Column(name = "MONGO_IP", nullable = true, length = 100)
    private String mongoIP;
    
    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

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

    public String getFolioErp() {
        return folioErp;
    }

    public void setFolioErp(String folioErp) {
        this.folioErp = folioErp;
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
        if (!(object instanceof EPagosBorrador)) {
            return false;
        }
        EPagosBorrador other = (EPagosBorrador) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fe.db.pagos.EPagosBorrador[ id=" + id + " ]";
    }
}
