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
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Index;

/**
 *
 * @author CarloGS
 */
@Entity
@Table(name = "E_AUTO_CFDI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EAutoCfdi.findAll", query = "SELECT m FROM EAutoCfdi m"),
    @NamedQuery(name = "EAutoCfdi.findAllReferencia", query = "SELECT m FROM EAutoCfdi m WHERE m.referencia = :referencia")
})
public class EAutoCfdi implements Serializable {

    private static final long serialVersionUID = 1L;
    
    protected EAutoCfdi() {}

    public EAutoCfdi(String correo, String concepto, String referencia, Date fecha, double importe, EClientes clientes) {
        this.correo = correo;
        this.concepto = concepto;
        this.referencia = referencia;
        this.fecha = fecha;
        this.importe = importe;
        this.clientes = clientes;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Index(name = "RFC_ACFDI_IDX")
    @Column(name = "RFC", nullable = true, length = 13)
    private String rfc;

    @Index(name = "CORREO_ACFDI_IDX")
    @Column(name = "CORREO", nullable = true)
    private String correo;

    @Index(name = "REF_ACFDI_IDX")
    @Column(name = "REFERENCIA", nullable = false, length = 45)
    private String referencia;

    @Index(name = "TICKET_ACFDI_IDX")
    @Column(name = "TICKET", nullable = true, length = 45)
    private String ticket;

    @Index(name = "FECHA_REC_IDX")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    @Column(name = "IMPORTE", nullable = true, scale = 12, precision = 2)
    private double importe;

    @Column(name = "CONCEPTO", nullable = true, length = 1000)
    private String concepto;

    @Column(name = "ESTATUS", nullable = false)
    private byte estatus = 0x0;

    @Column(name = "FORMA_PAGO", length = 2, nullable = true)
    private String formaPago;

    @Column(name = "FORMA_PAGO_DESC", length = 100, nullable = true)
    private String formaPagoDesc;

    @Column(name = "CVE_PROD_SERV", length = 8, nullable = true)
    private String cveProdServ;

    @Column(name = "CVE_PROD_SERV_DESC", length = 100, nullable = true)
    private String cveProdServDesc;

    @Column(name = "IVA", nullable = true)
    private boolean iva = true;
    
    @Column(name = "SERIE", nullable = true)
    private String serie;

    @ManyToOne
    private EEmitido emitido;

    @ManyToOne
    private EAcceso acceso;

    @ManyToOne
    private ECfdi cfdi;

    @ManyToOne
    private EClientes clientes;

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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String ref) {
        this.referencia = ref;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public byte getEstatus() {
        return estatus;
    }

    public void setEstatus(byte estatus) {
        this.estatus = estatus;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFormaPagoDesc() {
        return formaPagoDesc;
    }

    public void setFormaPagoDesc(String formaPagoDesc) {
        this.formaPagoDesc = formaPagoDesc;
    }

    public String getCveProdServ() {
        return cveProdServ;
    }

    public void setCveProdServ(String cveProdServ) {
        this.cveProdServ = cveProdServ;
    }

    public String getCveProdServDesc() {
        return cveProdServDesc;
    }

    public void setCveProdServDesc(String cveProdServDesc) {
        this.cveProdServDesc = cveProdServDesc;
    }

    public boolean isIva() {
        return iva;
    }

    public void setIva(boolean iva) {
        this.iva = iva;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public EEmitido getEmitido() {
        return emitido;
    }

    public void setEmitido(EEmitido emitido) {
        this.emitido = emitido;
    }

    public EAcceso getAcceso() {
        return acceso;
    }

    public void setAcceso(EAcceso acceso) {
        this.acceso = acceso;
    }

    public ECfdi getCfdi() {
        return cfdi;
    }

    public void setCfdi(ECfdi cfdi) {
        this.cfdi = cfdi;
    }

    public EClientes getClientes() {
        return clientes;
    }

    public void setClientes(EClientes clientes) {
        this.clientes = clientes;
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

    @Override
    public String toString() {
        return "fe.db.emision.EAutoCfdi[ id=" + id + " ]";
    }
}
