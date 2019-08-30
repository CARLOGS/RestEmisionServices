/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Index;

/**
 *
 * @author CARLO
 */
@Entity
@Table(name="D_ALUMNO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DAlumno.findAll", query = "SELECT m FROM DAlumno m"),
    @NamedQuery(name = "DAlumno.findAllN", query = "SELECT m FROM DAlumno m WHERE m.clientes = :clientes ORDER BY m.nombre"),
    @NamedQuery(name = "DAlumno.findAllNId", query = "SELECT m FROM DAlumno m WHERE m.id = :id"),
    @NamedQuery(name = "DAlumno.findAllNNombre", query = "SELECT m FROM DAlumno m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.nombre"),
    @NamedQuery(name = "DAlumno.findAllRfc", query = "SELECT m FROM DAlumno m WHERE m.numeroControl = :numeroControl"),
    @NamedQuery(name = "DAlumno.findAllNRfc", query = "SELECT m FROM DAlumno m WHERE m.numeroControl = :numeroControl AND m.clientes = :clientes ORDER BY m.numeroControl"),
    @NamedQuery(name = "DAlumno.findCtrl", query = "SELECT m FROM DAlumno m WHERE m.numeroControl = :numeroControl AND m.clientes = :clientes"),
    @NamedQuery(name = "DAlumno.findLikeCtrl", query = "SELECT m FROM DAlumno m WHERE m.numeroControl LIKE :numeroControl AND m.clientes = :clientes ORDER BY m.numeroControl"),
    @NamedQuery(name = "DAlumno.findLikeNombre", query = "SELECT m FROM DAlumno m WHERE m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.numeroControl"),
    @NamedQuery(name = "DAlumno.findAllNLikeRfc", query = "SELECT m FROM DAlumno m WHERE m.numeroControl LIKE :numeroControl AND m.clientes = :clientes ORDER BY m.numeroControl"),
    @NamedQuery(name = "DAlumno.findAllNRfcNombre", query = "SELECT m FROM DAlumno m WHERE m.numeroControl = :numeroControl AND m.nombre LIKE :nombre AND m.clientes = :clientes ORDER BY m.nombre"),
    @NamedQuery(name = "DAlumno.findAllNRfc_Or_Nombre", query = "SELECT m FROM DAlumno m WHERE (m.numeroControl LIKE :numeroControl OR m.nombre LIKE :nombre) AND m.clientes = :clientes ORDER BY m.nombre"),
    @NamedQuery(name = "DAlumno.findByAllByClientes", query = "SELECT m FROM DAlumno m WHERE m.clientes = :clientes")
})
public class DAlumno implements Serializable {
    private static final long serialVersionUID = 1L;

    // Contructor privado
    public DAlumno() {}

    // Constructor inicial
    public DAlumno(String numeroControl, String curp, String nombre) {
        this.numeroControl = numeroControl;
        this.curp = curp;
        this.nombre = nombre;
    }
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Index(name = "ALM_CTRL_IDX")
    @Column(name="NUMERO_CONTROL", nullable=false)
    private String numeroControl;

    @Column(name="CURP", nullable=true, length = 18)
    private String curp;

    @Index(name = "ALM_NOM_IDX")
    @Column(name="NOMBRE", nullable=false, length = 100)
    private String nombre;

    @Column(name="PRIMER_APELLIDO", nullable=false, length = 100)
    private String primerApellido;

    @Column(name="SEGUNDO_APELLIDO", nullable=true, length = 100)
    private String segundoApellido;

    @Column(name="ID_GENERO", nullable=false)
    private Integer idGenero;

    @Index(name = "ALM_NAC_IDX")
    @Column(name="FECHA_NACIMIENTO", nullable=false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Column(name="FOTO", nullable=true)
    private String foto;
    
    @Column(name="FIRMA_AUTOGRAFA", nullable=true)
    private String firmAutografa;
    
    @Index(name = "ALM_MAIL_IDX")
    @Column(name="EMAIL", nullable=true)
    private String mail;

    @Column(name="TELEFONO", nullable=true, length = 50)
    private String telefono;

    @Column(name="ESTATUS", nullable=false, length = 1)
    private String estatus = "1";

    @ManyToOne
    private DInstitucion clientes;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroControl() {
        return numeroControl;
    }

    public void setNumeroControl(String numeroControl) {
        this.numeroControl = numeroControl;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public Integer getIdGenero() {
        return idGenero;
    }

    public void setIdGenero(Integer idGenero) {
        this.idGenero = idGenero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getFirmAutografa() {
        return firmAutografa;
    }

    public void setFirmAutografa(String firmAutografa) {
        this.firmAutografa = firmAutografa;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public DInstitucion getClientes() {
        return clientes;
    }

    public void setClientes(DInstitucion clientes) {
        this.clientes = clientes;
    }

//    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DAlumno)) {
            return false;
        }
        DAlumno other = (DAlumno) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String str = (numeroControl != null && !"".equals(numeroControl) ? numeroControl + " ^ " : "" ) + nombre;
//        if ( str != null && str.length() > 80 )
//            str = str.substring(0,80) + " ...";

        return str;
    }
    
}
