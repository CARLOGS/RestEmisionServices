package fe.db.emision;

//import fe.db.pagos.EPagos;
import java.io.Serializable;
import java.util.Date;

//import javax.persistence.Fet;
//import javax.persistence.OneToMany;
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
//import java.util.Set;
//import org.hibernate.annotations.Fetch;
//import org.hibernate.annotations.FetchMode;
//import org.hibernate.annotations.BatchSize;

import org.hibernate.annotations.Index;

@Entity
@Table(name = "E_CLIENTES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EClientes.findAll", query = "SELECT m FROM EClientes m"),
    @NamedQuery(name = "EClientes.findAllRfc", query = "SELECT m FROM EClientes m WHERE m.rfc = :rfc"),
    @NamedQuery(name = "EClientes.findAllId", query = "SELECT m FROM EClientes m WHERE m.id = :id")
})
public class EClientes implements Serializable {

    private static final long serialVersionUID = 1L;

    protected EClientes() {
    }

    public EClientes(String rfc, String nombre, String email, int estatus, Date fecha, String accesoProveedores) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.email = email;
        this.estatus = estatus;
        this.fecha = fecha;
        this.accesoProveedores = accesoProveedores;
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

    @Column(name = "ACCESO_PROVEEDORES", nullable = false, length = 1)
    private String accesoProveedores = "0";

    @Column(name = "NUMERO_CLIENTE", nullable = true, length = 20)
    private String numeroCliente;

    @Column(name = "licencia_ID", nullable = true, insertable = false, updatable = false)
    private Long licenciaID;

    @Column(name = "COPIAR_CORREO", nullable = false, length = 1)
    private String copiarCorreo = "1";
    
    @Column(name = "CTRL_EXISTENCIAS", nullable = true)
    private boolean ctrlExistencias = false;
    
    /**
     * Campo para definir el modo de timbrado, 0-Desarrollo y 1-Producción
     */
    @Column(name = "MODO", nullable = false, length = 1)
    private int modo = 0;

//    @OneToMany(mappedBy = "cfdi")
//    @Fetch(FetchMode.SELECT)
//    @BatchSize(size=20)
//    private Set<ECfdi> cfdi;

//    @OneToMany(mappedBy = "pagos")
//    @Fetch(FetchMode.SELECT)
//    @BatchSize(size=20)
//    private Set<EPagos> pagos;

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

    public String getAccesoProveedores() {
        return accesoProveedores;
    }

    public void setAccesoProveedores(String accesoProveedores) {
        this.accesoProveedores = accesoProveedores;
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

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public boolean isCtrlExistencias() {
        return ctrlExistencias;
    }

    public void setCtrlExistencias(boolean ctrlExistencias) {
        this.ctrlExistencias = ctrlExistencias;
    }

//    public Set<ECfdi> getCfdi() {
//        return cfdi;
//    }
//
//    public void setCfdi(Set<ECfdi> cfdi) {
//        this.cfdi = cfdi;
//    }
//
//    public Set<EPagos> getPagos() {
//        return pagos;
//    }
//
//    public void setPagos(Set<EPagos> pagos) {
//        this.pagos = pagos;
//    }

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
