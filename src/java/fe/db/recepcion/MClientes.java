package fe.db.recepcion;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Index;

@Entity
@Table(name="CLIENTES")
public class MClientes implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected MClientes() {}
    
    public MClientes(String rfc, String nombre, String email, int estatus, Date fecha, String soloProveedores) {
    	this.rfc = rfc;
    	this.nombre = nombre;
    	this.email = email;
    	this.estatus = estatus;
    	this.fecha = fecha;
    	this.soloProveedores = soloProveedores;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
	private long id;

    @Index(name = "RFC_CTE_IDX")
    @Column(name = "RFC", nullable = false, length = 13)
	private String rfc;
	
    @Index(name = "NOMBRE_CTE_IDX")
    @Column(name = "NOMBRE", nullable = false)
	private String nombre;

    @Column(name = "EMAIL", nullable = true, length = 50)
	private String email;

    @Column(name = "TELEFONO", nullable = true, length = 20)
	private String telefono;

    @Column(name = "DIRECCION", nullable = true, length = 500)
	private String direccion;

    @Column(name = "ESTATUS", nullable = false)
	private int estatus;
	
    @Index(name = "FECHA_CTE_IDX")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA", nullable = false)
	private Date fecha;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_MODIFICACION", nullable = true)
	private Date fechaModificacion;

    @Column(name = "SUCURSAL", nullable = true, length = 60)
	private String sucursal;

    @Column(name = "RFC_ORIGEN", nullable = true, length = 13)
	private String rfcOrigen;

    @Column(name = "CODIGO_POSTAL", nullable = true, length = 10)
	private String codigoPostal;

    @Column(name = "SOLO_PROVEEDORES", nullable = false, length = 1)
	private String soloProveedores = "0";

    @Column(name = "NUMERO_CLIENTE", nullable = true, length = 20)
	private String numeroCliente;

    @Column(name = "licencia_ID")
	private Long licenciaID;

    @Column(name = "COPIAR_CORREO", nullable = false, length = 1)
	private String copiarCorreo = "1";

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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getEstatus() {
		return estatus;
	}

	public void setEstatus(int estatus) {
		this.estatus = estatus;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getRfcOrigen() {
		return rfcOrigen;
	}

	public void setRfcOrigen(String rfcOrigen) {
		this.rfcOrigen = rfcOrigen;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getSoloProveedores() {
		return soloProveedores;
	}

	public void setSoloProveedores(String soloProveedores) {
		this.soloProveedores = soloProveedores;
	}

	public String getNumeroCliente() {
		return numeroCliente;
	}

	public void setNumeroCliente(String numeroCliente) {
		this.numeroCliente = numeroCliente;
	}

	public Long getLicenciaID() {
		return licenciaID;
	}

	public void setLicenciaID(Long licenciaID) {
		this.licenciaID = licenciaID;
	}

    public String getCopiarCorreo() {
		return copiarCorreo;
	}

	public void setCopiarCorreo(String copiarCorreo) {
		this.copiarCorreo = copiarCorreo;
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
