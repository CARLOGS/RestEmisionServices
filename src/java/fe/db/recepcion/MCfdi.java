package fe.db.recepcion;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Index;

@Entity
@Table(name="R_CFDI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MCfdi.findAll", query = "SELECT m FROM MCfdi m"),
    @NamedQuery(name = "MCfdi.findUuid", query = "SELECT m FROM MCfdi m WHERE m.uuid = :uuid")
})
public class MCfdi implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected MCfdi() {}
    
    public MCfdi(String rfc, String nombre, String uuid, Date fecha, Date fechaRecibido, double importe ) {
    	this.rfc = rfc;
    	this.nombre = nombre;
    	this.uuid = uuid;
    	this.fecha = fecha;
    	this.fechaRecibido = fechaRecibido;
    	this.importe = importe;
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
    
    @Column(name = "IMPORTE", nullable = false, scale = 12, precision = 2)
	private double importe;
    
    @Column(name = "addendas_ID", nullable = true)
	private long addendas = 0;
    
    @Index(name = "CFD_IDX")
    @Column(name = "clientes_ID", nullable = true)
	private Long clientes = 1l;
    
    @Column(name = "proveedores_ID", nullable = true)
	private Long proveedores = 1l;
    
    @Column(name = "ESTADO_DOCUMENTO", nullable = false)
	private int estadoDocumento = 1;
    
    @Column(name = "PDF", nullable = false)
    private int pdf = 0;

    @Column(name = "MONGO_COLLECTION", nullable = true, length = 10)
    private String mongoCollection;

    @Column(name = "MONGO_IP", nullable = true, length = 100)
	private String mongoIP;

    @Column(name = "NUMERO_FACTURA", nullable = true, length = 31)
	private String numeroFactura;

    @Column(name = "ESTATUS_WF", nullable = false)
	private int estatusWF = 1;

    @Index(name = "RRFC_REC_IDX")
    @Column(name = "RRFC", nullable = false, length = 13)
    private String rRfc;
	
    @Index(name = "RNOMBRE_REC_IDX")
    @Column(name = "RNOMBRE", nullable = false)
    private String rNombre;
	
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
    
    @Index(name = "CFDI_TERCERO_IDX")
    @Column(name = "RFC_TERCERO", nullable = true, length = 13)
	private String rfcTercero;

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

    public String getRRfc() {
		return rRfc;
	}

	public void setRRfc(String rRfc) {
		this.rRfc = rRfc;
	}

	public String getRNombre() {
		return rNombre;
	}

	public void setRNombre(String rNombre) {
		this.rNombre = rNombre;
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

	public Date getFechaRecibido() {
		return fechaRecibido;
	}

	public void setFechaRecibido(Date fechaRecibido) {
		this.fechaRecibido = fechaRecibido;
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

	public Long getClientes() {
		return clientes;
	}

	public void setClientes(Long clientes) {
		this.clientes = clientes;
	}

	public Long getProveedores() {
		return proveedores;
	}

	public void setProveedores(Long proveedores) {
		this.proveedores = proveedores;
	}

	public int getEstadoDocumento() {
		return estadoDocumento;
	}

	public void setEstadoDocumento(int estadoDocumento) {
		this.estadoDocumento = estadoDocumento;
	}

	public int getPdf() {
		return pdf;
	}

	public void setPdf(int pdf) {
		this.pdf = pdf;
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

	public int getEstatusWF() {
		return estatusWF;
	}

	public void setEstatusWF(int estatusWF) {
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

    public String getRfcTercero() {
        return rfcTercero;
    }

    public void setRfcTercero(String rfcTercero) {
        this.rfcTercero = rfcTercero;
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
