package fe.db.emision;

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
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "E_CFDI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ECfdi.findAll", query = "SELECT m FROM ECfdi m"),
    @NamedQuery(name = "ECfdi.findAllN", query = "SELECT m FROM ECfdi m WHERE m.clientes = :clientes AND m.fecha >= :fIni AND m.fecha <= :fFin AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllN2", query = "SELECT m FROM ECfdi m WHERE m.clientes = :clientes AND YEAR(m.fecha) = :anio AND MONTH(m.fecha) = :mes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllN3", query = "SELECT m FROM ECfdi m WHERE m.clientes = :clientes AND YEAR(m.fecha) = :anio ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllId", query = "SELECT m FROM ECfdi m WHERE m.id = :id"),
    @NamedQuery(name = "ECfdi.findAllNNombre", query = "SELECT m FROM ECfdi m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNNombreRango", query = "SELECT m FROM ECfdi m WHERE m.fecha > :fIni AND m.fecha <= :fFin AND m.nombre LIKE :nombre AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNRfc", query = "SELECT m FROM ECfdi m WHERE m.rfc = :rfc AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNRfcRango", query = "SELECT m FROM ECfdi m WHERE m.fecha > :fIni AND m.fecha <= :fFin AND m.rfc = :rfc AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNNoFactura", query = "SELECT m FROM ECfdi m WHERE m.numeroFactura = :numeroFactura AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findCfdiUuid", query = "SELECT m FROM ECfdi m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "ECfdi.findAllUuid", query = "SELECT m FROM ECfdi m WHERE m.uuid = :uuid AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido)"),
    @NamedQuery(name = "ECfdi.findAllUuidFecha", query = "SELECT m FROM ECfdi m WHERE m.uuid = :uuid AND m.fecha = :fecha"),
    @NamedQuery(name = "ECfdi.findAllNUuid", query = "SELECT m FROM ECfdi m WHERE m.uuid = :uuid AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido)"),
    @NamedQuery(name = "ECfdi.findAllNEdoDoc", query = "SELECT m FROM ECfdi m WHERE m.estadoDocumento = :estadoDocumento AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNWF", query = "SELECT m FROM ECfdi m WHERE m.estatusWF = :wf AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNNumFac", query = "SELECT m FROM ECfdi m WHERE m.numeroFactura = :numFac AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNFolErp", query = "SELECT m FROM ECfdi m WHERE m.folioErp = :folErp AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findByAllByClientes", query = "SELECT m FROM ECfdi m WHERE m.clientes = :clientes")
})
public class ECfdi implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ECfdi() {}

    public ECfdi(String rfc, String nombre, String uuid, Date fecha, Double importe, EClientes clientes) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.uuid = uuid;
        this.fecha = fecha;
        this.importe = importe;
        this.clientes = clientes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Index(name = "RFC_REC_IDX")
    @Column(name = "RFC", nullable = false, length = 13)
    private String rfc;

    @Index(name = "NOMBRE_REC_IDX")
    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Index(name = "UUID_REC_IDX")
    @Column(name = "UUID", nullable = false, length = 45)
    private String uuid;

    @Index(name = "FECHA_REC_IDX")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "IMPORTE", nullable = false, scale = 12, precision = 2)
    private Double importe;
    
    @Column(name = "SERIE", nullable = true, length = 20)
    private String serie;
    
    @Column(name = "FOLIO", nullable = true, length = 20)
    private String folio;
    
    @Column(name = "FOLIO_ERP", nullable = true, length = 20)
    private String folioErp;

    @Column(name = "NUMERO_FACTURA", nullable = false, length = 31)
    private String numeroFactura;

    @Column(name = "TIPO_DOCUMENTO", nullable = false, length = 50)
    private String tipoDocumento = "FACTURA";
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_CANCELACION", nullable = true)
    private Date fechaCancelacion;

    @Column(name = "ESTATUS_ENVIOM", nullable = false)
    private Integer estatusEnvioM = 0;
    
    @Column(name = "ESTADO_DOCUMENTO", nullable = false)
    private Integer estadoDocumento = 1;

    @Column(name = "MODO", nullable = false)
    private Integer modo = 0;

    @Column(name = "MONGO_COLLECTION", nullable = true, length = 10)
    private String mongoCollection;

    @Column(name = "MONGO_IP", nullable = true, length = 100)
    private String mongoIP;

    @Column(name = "ESTATUS_WF", nullable = false)
    private Long estatusWF = 1l;

    @Index(name = "CFDI_ORI_IDX")
    @Column(name = "ORIGEN", nullable = false)
    private Short origen = 1;

    @Index(name = "CFDI_ADJ_IDX")
    @Column(name = "ADJUNTO", nullable = false)
    private Short adjunto = 0;

    @Column(name = "MONEDA", nullable = false)
    private String moneda = "MXN";

    @Column(name = "TIPO_CAMBIO", nullable = false)
    private Double tipoCambio = 1d;

    @ManyToOne
    private EAcceso acceso;
    
    @Column(name = "items_ID", nullable = true)
    private Long items;

    @Column(name = "addendas_ID", nullable = true)
    private Long addendas = 0l;

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

    @ManyToOne
    private EEmitido emitido;

    public Short getOrigen() {
        return origen;
    }

    public void setOrigen(Short origen) {
        this.origen = origen;
    }

    public Short getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(Short adjunto) {
        this.adjunto = adjunto;
    }

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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Long getAddendas() {
        return addendas;
    }

    public void setAddendas(Long addendas) {
        this.addendas = addendas;
    }

    public EClientes getClientes() {
        return clientes;
    }

    public void setClientes(EClientes clientes) {
        this.clientes = clientes;
    }

    public Integer getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(int estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public Integer getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public String getMongoCollection() {
        return mongoCollection;
    }

    public void setMongoCollection(String mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public String getMongoIP() {
        return mongoIP;
    }

    public void setMongoIP(String mongoIP) {
        this.mongoIP = mongoIP;
    }

    public String getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(String numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public Long getEstatusWF() {
        return estatusWF;
    }

    public void setEstatusWF(Long estatusWF) {
        this.estatusWF = estatusWF;
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

    public String getFolioErp() {
        return folioErp;
    }

    public void setFolioErp(String folioErp) {
        this.folioErp = folioErp;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Date getFechaCancelacion() {
        return fechaCancelacion;
    }

    public void setFechaCancelacion(Date fechaCancelacion) {
        this.fechaCancelacion = fechaCancelacion;
    }

    public Integer getEstatusEnvioM() {
        return estatusEnvioM;
    }

    public void setEstatusEnvioM(int estatusEnvioM) {
        this.estatusEnvioM = estatusEnvioM;
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

    public EAcceso getAcceso() {
        return acceso;
    }

    public void setAcceso(EAcceso acceso) {
        this.acceso = acceso;
    }

    public Long getItems() {
        return items;
    }

    public void setItems(Long items) {
        this.items = items;
    }

    public EEmitido getEmitido() {
        return emitido;
    }

    public void setEmitido(EEmitido emitido) {
        this.emitido = emitido;
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
}
