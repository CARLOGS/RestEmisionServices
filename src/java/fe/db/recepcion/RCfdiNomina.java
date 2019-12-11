
package fe.db.recepcion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "R_CFDI_NOMINA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RCfdiNomina.findAll", query = "SELECT m FROM RCfdiNomina m"),
    @NamedQuery(name = "RCfdiNomina.findUuid", query = "SELECT m FROM RCfdiNomina m WHERE m.uuid = :uuid")
})
public class RCfdiNomina implements Serializable {

    private static final long serialVersionUID = 1L;

    protected RCfdiNomina() {
    }

    public RCfdiNomina(String rfc, String nombre, String uuid, Date fecha, Date fechaRecibido, double monto, RClientes clientes) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.uuid = uuid;
        this.fecha = fecha;
        this.fechaRecibido = fechaRecibido;
        this.monto = monto;
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

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_RECIBIDO", nullable = false)
    private Date fechaRecibido;

    @Column(name = "PERCEPCIONES", nullable = true, scale = 12, precision = 2)
    private double percepciones;

    @Column(name = "DEDUCCIONES", nullable = true, scale = 12, precision = 2)
    private double deducciones;

    @Column(name = "OTROS", nullable = true, scale = 12, precision = 2)
    private double otros;

    @Column(name = "TIPO_CONTRATO", nullable = true, length = 2)
    private String tipoContrato;

    @Column(name = "MONTO", nullable = false, scale = 12, precision = 2)
    private double monto;

    @Index(name = "CFD_IDX")
    @ManyToOne
    private RClientes clientes;

    @Column(name = "ESTADO_DOCUMENTO", nullable = false)
    private int estadoDocumento = 1;

    @Column(name = "MONGO_COLLECTION", nullable = true, length = 10)
    private String mongoCollection;

    @Column(name = "MONGO_IP", nullable = true, length = 100)
    private String mongoIP;

    @Column(name = "NUMERO_FACTURA", nullable = true, length = 31)
    private String numeroFactura;

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

    public Date getFechaRecibido() {
        return fechaRecibido;
    }

    public void setFechaRecibido(Date fechaRecibido) {
        this.fechaRecibido = fechaRecibido;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public double getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(double percepciones) {
        this.percepciones = percepciones;
    }

    public double getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(double deducciones) {
        this.deducciones = deducciones;
    }

    public double getOtros() {
        return otros;
    }

    public void setOtros(double otros) {
        this.otros = otros;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public RClientes getClientes() {
        return clientes;
    }

    public void setClientes(RClientes clientes) {
        this.clientes = clientes;
    }

    public int getEstadoDocumento() {
        return estadoDocumento;
    }

    public void setEstadoDocumento(int estadoDocumento) {
        this.estadoDocumento = estadoDocumento;
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
