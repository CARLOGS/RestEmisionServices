package fe.db.emision;

/**
 *
 * @Author Carlo Garcia Sanchez
 * @Date 02-07-2011
 *
 * Tabla de Acceso para almacenar e
 * identificar a usuarios
 *
 */
 
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

@Entity
@Table(name="D_ACCESO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DAcceso.findAll", query = "SELECT m FROM DAcceso m"),
    @NamedQuery(name = "DAcceso.findAllId", query = "SELECT m FROM DAcceso m WHERE m.id = :id"),
    @NamedQuery(name = "DAcceso.findAllN", query = "SELECT m FROM DAcceso m WHERE m.clientes = :clientes ORDER BY m.usuario"),
    @NamedQuery(name = "DAcceso.findAllNUs", query = "SELECT m FROM DAcceso m WHERE m.usuario = :usuario"),
    @NamedQuery(name = "DAcceso.findAllNUsuario", query = "SELECT m FROM DAcceso m WHERE m.usuario = :usuario AND m.clientes = :clientes ORDER BY m.usuario"),
    @NamedQuery(name = "DAcceso.findAllNNombre", query = "SELECT m FROM DAcceso m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.nombre")
})
public class DAcceso implements Serializable {
    private static final long serialVersionUID = 1L;

    // Contructor privado
    protected DAcceso() {}

    // Constructor inicial
    public DAcceso(String usuario, String clave, String nombre, String email, DInstitucion clientes, Date fecha) {
            this.usuario = usuario;
            this.clave = clave;
            this.nombre = nombre;
            this.email = email;
            this.clientes = clientes;
            this.fecha = fecha;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private Long id;
    
    @Column(name="USUARIO", nullable=false, length = 28)
    private String usuario;
    
    @Column(name="CLAVE", nullable=false, length = 40)
    private String clave;
    
    @Column(name="ULTIMA_CLAVE1", nullable=true, length = 40)
    private String ultimaClave1;
    
    @Column(name="ULTIMA_CLAVE2", nullable=true, length = 40)
    private String ultimaClave2;
    
    @Column(name="ULTIMA_CLAVE3", nullable=true, length = 40)
    private String ultimaClave3;
    
    @Column(name="EMAIL", nullable=true, length = 150)
    private String email;
    
    @Column(name="NOMBRE", nullable=true, length = 100)
    private String nombre;
    
    @Column(name="STATUS", nullable=false)
    private int status = 1;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="FECHA", nullable=false)
    private Date fecha;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="FECHA_MODIFICACION", nullable=true)
    private Date fechaModificacion;
    
    @Column(name="NINTENTOS", nullable=false)
    private int nIntentos;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="ULTIMOACCESO", nullable=false)
    private Date ultimoAcceso;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="ULTIMOINTENTO", nullable=true)
    private Date ultimoIntento;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="ACCESOACTUAL", nullable=true)
    private Date accesoActual;
    
    @Column(name="NUMERO", nullable=true, length = 50)
    private String numero;
    
    @Column(name="tipoAcceso_ID", nullable=true)
    private Integer tipoAcceso;
    
//    @Column(name="clientes_ID", nullable=true)
    @ManyToOne
    private DInstitucion clientes;
    
    @ManyToOne
    private DPerfiles perfiles;
    
    @Column(name="CAMBIA_PW", nullable=true)
    private int cambiaPw;

    @Column(name="CONECTADO", nullable=false)
    private int conectado = 0;
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="FECHA_SALIR", nullable=true)
    private Date fechaSalir;

    // ***** Setter & Getters *****
    
    public Date getFechaSalir() {
        return fechaSalir;
    }

    public void setFechaSalir(Date fechaSalir) {
        this.fechaSalir = fechaSalir;
    }

    public int getConectado() {
        return conectado;
    }

    public void setConectado(int conectado) {
        this.conectado = conectado;
    }

    public int getCambiaPw() {
        return cambiaPw;
    }

    public void setCambiaPw(int cambiaPw) {
        this.cambiaPw = cambiaPw;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String pwActual) {
        this.clave = pwActual;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public int getnIntentos() {
        return nIntentos;
    }

    public void setnIntentos(int nIntentos) {
        this.nIntentos = nIntentos;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public Date getUltimoIntento() {
        return ultimoIntento;
    }

    public void setUltimoIntento(Date ultimoIntento) {
        this.ultimoIntento = ultimoIntento;
    }

    public Integer getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(Integer tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
    }

    public DInstitucion getClientes() {
        return clientes;
    }

    public void setClientes(DInstitucion clientes) {
        this.clientes = clientes;
    }

    public DPerfiles getPerfiles() {
        return perfiles;
    }

    public void setPerfiles(DPerfiles perfiles) {
        this.perfiles = perfiles;
    }

    public String getUltimaClave1() {
        return ultimaClave1;
    }

    public void setUltimaClave1(String ultimaClave1) {
        this.ultimaClave1 = ultimaClave1;
    }

    public String getUltimaClave2() {
        return ultimaClave2;
    }

    public void setUltimaClave2(String ultimaClave2) {
        this.ultimaClave2 = ultimaClave2;
    }

    public String getUltimaClave3() {
        return ultimaClave3;
    }

    public void setUltimaClave3(String ultimaClave3) {
        this.ultimaClave3 = ultimaClave3;
    }

    public Date getAccesoActual() {
        return accesoActual;
    }

    public void setAccesoActual(Date accesoActual) {
        this.accesoActual = accesoActual;
    }

    @Override 
    public int hashCode() { 
        int result = 1; 
        return result; 
    }

    @Override 
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DAcceso)) {
            return false;
        }
        DAcceso other = (DAcceso) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    /**
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
}
