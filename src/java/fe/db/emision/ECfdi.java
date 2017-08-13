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
    @NamedQuery(name = "ECfdi.findAllN", query = "SELECT m FROM ECfdi m WHERE m.clientes = :clientes AND m.fecha >= :fIni AND m.fecha <= :fFin ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllN2", query = "SELECT m FROM ECfdi m WHERE m.clientes = :clientes AND m.fecha >= :fIni AND m.fecha < :fFin ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllId", query = "SELECT m FROM ECfdi m WHERE m.id = :id"),
    @NamedQuery(name = "ECfdi.findAllNNombre", query = "SELECT m FROM ECfdi m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNRfc", query = "SELECT m FROM ECfdi m WHERE m.rfc = :rfc AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNNoFactura", query = "SELECT m FROM ECfdi m WHERE m.numeroFactura = :numeroFactura AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllUuid", query = "SELECT m FROM ECfdi m WHERE m.uuid = :uuid"),
    @NamedQuery(name = "ECfdi.findAllNUuid", query = "SELECT m FROM ECfdi m WHERE m.uuid = :uuid AND m.clientes = :clientes"),
    @NamedQuery(name = "ECfdi.findAllNEdoDoc", query = "SELECT m FROM ECfdi m WHERE m.estadoDocumento = :estadoDocumento AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findAllNWF", query = "SELECT m FROM ECfdi m WHERE m.estatusWF = :wf AND m.clientes = :clientes ORDER BY m.fecha DESC"),
    @NamedQuery(name = "ECfdi.findByAllByClientes", query = "SELECT m FROM ECfdi m WHERE m.clientes = :clientes")
})
public class ECfdi implements Serializable {

    private static final long serialVersionUID = 1L;

    protected ECfdi() {}

    public ECfdi(String rfc, String nombre, String uuid, Date fecha, double importe, EClientes clientes) {
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
    private EAcceso acceso;
    
    @Column(name = "items_ID", nullable = true)
    private Long items;

    @Column(name = "addendas_ID", nullable = true)
    private long addendas = 0;

    @ManyToOne
    private EClientes clientes;

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

    public EClientes getClientes() {
        return clientes;
    }

    public void setClientes(EClientes clientes) {
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
