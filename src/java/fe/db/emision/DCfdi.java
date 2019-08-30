package fe.db.emision;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "D_CFDI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DCfdi.findAll", query = "SELECT m FROM DCfdi m"),
    @NamedQuery(name = "DCfdi.findAllN", query = "SELECT m FROM DCfdi m WHERE m.clientes = :clientes AND m.fecha >= :fIni AND m.fecha <= :fFin AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllN0", query = "SELECT m FROM DCfdi m WHERE m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllN2", query = "SELECT m FROM DCfdi m WHERE m.clientes = :clientes AND YEAR(m.fecha) = :anio AND MONTH(m.fecha) = :mes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllN3", query = "SELECT m FROM DCfdi m WHERE m.clientes = :clientes AND YEAR(m.fecha) = :anio ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllId", query = "SELECT m FROM DCfdi m WHERE m.id = :id"),
    @NamedQuery(name = "DCfdi.findAllNNombre", query = "SELECT m FROM DCfdi m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllNNombreRango", query = "SELECT m FROM DCfdi m WHERE m.fecha > :fIni AND m.fecha <= :fFin AND m.nombre LIKE :nombre AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllNRfc", query = "SELECT m FROM DCfdi m WHERE m.rfc = :rfc AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllNRfcRango", query = "SELECT m FROM DCfdi m WHERE m.fecha > :fIni AND m.fecha <= :fFin AND m.rfc = :rfc AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllNNoFactura", query = "SELECT m FROM DCfdi m WHERE m.numeroFactura = :numeroFactura AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findCfdiUuid", query = "SELECT m FROM DCfdi m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "DCfdi.findAllUuid", query = "SELECT m FROM DCfdi m WHERE m.uuid = :uuid AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido)"),
    @NamedQuery(name = "DCfdi.findAllUuidFecha", query = "SELECT m FROM DCfdi m WHERE m.uuid = :uuid AND m.fecha = :fecha"),
    @NamedQuery(name = "DCfdi.findAllNUuid", query = "SELECT m FROM DCfdi m WHERE m.uuid = :uuid AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido)"),
    @NamedQuery(name = "DCfdi.findAllNEdoDoc", query = "SELECT m FROM DCfdi m WHERE m.estadoDocumento = :estadoDocumento AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllNWF", query = "SELECT m FROM DCfdi m WHERE m.estatusWF = :wf AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllNNumFac", query = "SELECT m FROM DCfdi m WHERE m.numeroFactura = :numFac AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findAllNFolErp", query = "SELECT m FROM DCfdi m WHERE m.folioErp = :folErp AND m.clientes = :clientes AND ((:emitido IS NULL and m.emitido IS NULL) or m.emitido = :emitido) ORDER BY m.fecha DESC"),
    @NamedQuery(name = "DCfdi.findByAllByClientes", query = "SELECT m FROM DCfdi m WHERE m.clientes = :clientes")
})
public class DCfdi implements Serializable {

    private static final long serialVersionUID = 1L;

    protected DCfdi() {}

    public DCfdi(String rfc, String nombre, String uuid, Date fecha, double importe, DInstitucion clientes) {
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
    private long id;

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
    private double importe;
    
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
    private int estatusEnvioM = 0;
    
    @Column(name = "ESTADO_DOCUMENTO", nullable = false)
    private int estadoDocumento = 1;

    @Column(name = "MODO", nullable = false)
    private int modo = 0;

    @Column(name = "MONGO_COLLECTION", nullable = true, length = 10)
    private String mongoCollection;

    @Column(name = "MONGO_IP", nullable = true, length = 100)
    private String mongoIP;

    @Column(name = "ESTATUS_WF", nullable = false)
    private Long estatusWF = 1l;

    @Index(name = "CFDI_ORI_IDX")
    @Column(name = "ORIGEN", nullable = false)
    private short origen = 1;

    @Index(name = "CFDI_ADJ_IDX")
    @Column(name = "ADJUNTO", nullable = false)
    private short adjunto = 0;

    @Column(name = "MONEDA", nullable = false)
    private String moneda = "MXN";

    @Column(name = "TIPO_CAMBIO", nullable = false)
    private double tipoCambio = 1d;

    @ManyToOne
    private DAcceso acceso;
    
    @Column(name = "items_ID", nullable = true)
    private Long items;

    @Column(name = "addendas_ID", nullable = true)
    private long addendas = 0;

    @ManyToOne
    private DInstitucion clientes;

    @ManyToOne
    private DCampus emitido;

    @ManyToOne(cascade = {CascadeType.ALL},fetch=FetchType.EAGER, optional = true, targetEntity = DCarrera.class)
    private DCarrera carrera;

    @ManyToOne(cascade = {CascadeType.ALL},fetch=FetchType.EAGER, optional = true, targetEntity = DAlumno.class)
    private DAlumno alumno;

    public short getOrigen() {
        return origen;
    }

    public void setOrigen(short origen) {
        this.origen = origen;
    }

    public short getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(short adjunto) {
        this.adjunto = adjunto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public long getAddendas() {
        return addendas;
    }

    public void setAddendas(long addendas) {
        this.addendas = addendas;
    }

    public DInstitucion getClientes() {
        return clientes;
    }

    public void setClientes(DInstitucion clientes) {
        this.clientes = clientes;
    }

    public int getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(int estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
    }

    public int getModo() {
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

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
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

    public int getEstatusEnvioM() {
        return estatusEnvioM;
    }

    public void setEstatusEnvioM(int estatusEnvioM) {
        this.estatusEnvioM = estatusEnvioM;
    }

    public DAcceso getAcceso() {
        return acceso;
    }

    public void setAcceso(DAcceso acceso) {
        this.acceso = acceso;
    }

    public Long getItems() {
        return items;
    }

    public void setItems(Long items) {
        this.items = items;
    }

    public DCampus getEmitido() {
        return emitido;
    }

    public void setEmitido(DCampus emitido) {
        this.emitido = emitido;
    }

    public DCarrera getCarrera() {
        return carrera;
    }

    public void setCarrera(DCarrera carrera) {
        this.carrera = carrera;
    }

    public DAlumno getAlumno() {
        return alumno;
    }

    public void setAlumno(DAlumno alumno) {
        this.alumno = alumno;
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
