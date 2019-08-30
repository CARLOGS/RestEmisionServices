package fe.db.dec;

/**
 *
 * @Author Carlo Garc�a S�nchez
 * @Date 04-10-2012
 *
 * Tabla de M_CFD para almacenar documentos
 * CFD / CFDi
 * 
 *
 */
 
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Index;

@Entity
@Table(name="LICENCIAS", uniqueConstraints = {@UniqueConstraint(columnNames={"RFC"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MLicencias.findAll", query = "SELECT m FROM MLicencias m"),
    @NamedQuery(name = "MLicencias.findAllN", query = "SELECT m FROM MLicencias m WHERE m.fecha >= :fIni AND m.fecha <= :fFin"),
    @NamedQuery(name = "MLicencias.findAllId", query = "SELECT m FROM MLicencias m WHERE m.id = :id"),
    @NamedQuery(name = "MLicencias.findAllNRazonSocial", query = "SELECT m FROM MLicencias m WHERE m.razonSocial LIKE :razonSocial"),
    @NamedQuery(name = "MLicencias.findAllNRfc", query = "SELECT m FROM MLicencias m WHERE m.rfc = :rfc")
})
public class MLicencias implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID", nullable=false)
    private Long id = 0l;

    @Index(name = "LIC_RS_IDX") 
    @Column(name="RazonSocial", nullable=true, length=100)
    private String razonSocial = "";

    @Column(name="RFC", nullable=false, length=13)
    private String rfc = "XAXX010101000";

    @Column(name="Calle", nullable=true, length=45)
    private String calle = "";
    @Column(name="NoExterior", nullable=true, length=45)
    private String noExt = "";
    @Column(name="NoInterior", nullable=true, length=45)
    private String noInt = "";
    @Column(name="Colonia", nullable=true, length=45)
    private String col = "";
    @Column(name="Estado", nullable=true, length=45)
    private String edo = "";
    @Column(name="Municipio", nullable=true, length=45)
    private String mun = "";
    @Column(name="Localidad", nullable=true, length=45)
    private String loc = "";
    @Column(name="CP", nullable=true, length=45)
    private String cp = "";
    @Column(name="Pais", nullable=true, length=45)
    private String pais = "";
    @Column(name="Contacto", nullable=true)
    private String contacto = "";
    @Column(name="Factura", nullable=true, length=45)
    private String factura = "";

    @Column(name="RazonSocialF", nullable=true, length=100)
    private String razonSocialF = "";

    @Column(name="RFCF", nullable=true, length=13)
    private String rfcF = "XAXX010101000";

    @Column(name="CalleF", nullable=true, length=45)
    private String calleF = "";
    @Column(name="NoExteriorF", nullable=true, length=45)
    private String noExtF = "";
    @Column(name="NoInteriorF", nullable=true, length=45)
    private String noIntF = "";
    @Column(name="ColoniaF", nullable=true, length=45)
    private String colF = "";
    @Column(name="EstadoF", nullable=true, length=45)
    private String edoF = "";
    @Column(name="MunicipioF", nullable=true, length=45)
    private String munF = "";
    @Column(name="LocalidadF", nullable=true, length=45)
    private String locF = "";
    @Column(name="CPF", nullable=true, length=45)
    private String cpF = "";
    @Column(name="PaisF", nullable=true, length=45)
    private String paisF = "";

    @Column(name="Email", nullable=true, length=150)
    private String email = "";
    @Column(name="Telefono", nullable=true, length=45)
    private String tel = "";
    @Column(name="Celular", nullable=true, length=45)
    private String cel = "";

    @Index(name = "LIC_FECHA_IDX") 
    @Column(name="Fecha", nullable=false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha = new Date();
    @Lob
    @Column(name="Licencia", nullable=false)
    private byte[] lic = new byte[0];
    @Column(name="Status", nullable=false)
    private int status = 1;
    @Column(name="Numero_Factura", nullable=true, length=30)
    private String noFac = "";

    @Index(name = "LIC_AGTE_IDX")
    @Column(name="Agente", nullable=false, length=45)
    private String agte = "";
    @Column(name="Inicia", nullable=false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date inicia = new Date();
    @Column(name="Vence", nullable=false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date vence = new Date();
    @Column(name="Pago", nullable=false)
    private int pago = 0;
    @Column(name="Tipo_Pago", nullable=true, length=200)
    private String tipoPago = "";
    @Column(name="Fecha_Pago", nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fPago = new Date();
    @Column(name="Importe", nullable=false, precision=13, scale=2)
    private double importe = 0;
    @Index(name = "LIC_TIPO_IDX") 
    @Column(name="Tipo", nullable=true, length=200)
    private String tipo = "";
    @Column(name="FOLIOS", nullable=false)
    private int folios = 0;
    @Column(name="FOLIOS_USADOS", nullable=false)
    private int fUsados = 0;
    @Column(name="REGIMEN", length = 3, nullable=true)
    private String regimen;

    protected MLicencias() {}

    public MLicencias(String rfc, byte[] lic, Date fecha, Date inicia, Date vence, String tipo) {
            this.rfc = rfc;
            this.lic = lic;
            this.fecha = fecha;
            this.inicia = inicia;
            this.vence = vence;
            this.tipo = tipo;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the rfc
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * @param rfc the rfc to set
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the noExt
     */
    public String getNoExt() {
        return noExt;
    }

    /**
     * @param noExt the noExt to set
     */
    public void setNoExt(String noExt) {
        this.noExt = noExt;
    }

    /**
     * @return the noInt
     */
    public String getNoInt() {
        return noInt;
    }

    /**
     * @param noInt the noInt to set
     */
    public void setNoInt(String noInt) {
        this.noInt = noInt;
    }

    /**
     * @return the col
     */
    public String getCol() {
        return col;
    }

    /**
     * @param col the col to set
     */
    public void setCol(String col) {
        this.col = col;
    }

    /**
     * @return the edo
     */
    public String getEdo() {
        return edo;
    }

    /**
     * @param edo the edo to set
     */
    public void setEdo(String edo) {
        this.edo = edo;
    }

    /**
     * @return the mun
     */
    public String getMun() {
        return mun;
    }

    /**
     * @param mun the mun to set
     */
    public void setMun(String mun) {
        this.mun = mun;
    }

    /**
     * @return the loc
     */
    public String getLoc() {
        return loc;
    }

    /**
     * @param loc the loc to set
     */
    public void setLoc(String loc) {
        this.loc = loc;
    }

    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }

    /**
     * @param pais the pais to set
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * @return the contacto
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * @param contacto the contacto to set
     */
    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    /**
     * @return the factura
     */
    public String getFactura() {
        return factura;
    }

    /**
     * @param factura the factura to set
     */
    public void setFactura(String factura) {
        this.factura = factura;
    }

    /**
     * @return the razonSocialF
     */
    public String getRazonSocialF() {
        return razonSocialF;
    }

    /**
     * @param razonSocialF the razonSocialF to set
     */
    public void setRazonSocialF(String razonSocialF) {
        this.razonSocialF = razonSocialF;
    }

    /**
     * @return the rfcF
     */
    public String getRfcF() {
        return rfcF;
    }

    /**
     * @param rfcF the rfcF to set
     */
    public void setRfcF(String rfcF) {
        this.rfcF = rfcF;
    }

    /**
     * @return the calleF
     */
    public String getCalleF() {
        return calleF;
    }

    /**
     * @param calleF the calleF to set
     */
    public void setCalleF(String calleF) {
        this.calleF = calleF;
    }

    /**
     * @return the noExtF
     */
    public String getNoExtF() {
        return noExtF;
    }

    /**
     * @param noExtF the noExtF to set
     */
    public void setNoExtF(String noExtF) {
        this.noExtF = noExtF;
    }

    /**
     * @return the noIntF
     */
    public String getNoIntF() {
        return noIntF;
    }

    /**
     * @param noIntF the noIntF to set
     */
    public void setNoIntF(String noIntF) {
        this.noIntF = noIntF;
    }

    /**
     * @return the colF
     */
    public String getColF() {
        return colF;
    }

    /**
     * @param colF the colF to set
     */
    public void setColF(String colF) {
        this.colF = colF;
    }

    /**
     * @return the edoF
     */
    public String getEdoF() {
        return edoF;
    }

    /**
     * @param edoF the edoF to set
     */
    public void setEdoF(String edoF) {
        this.edoF = edoF;
    }

    /**
     * @return the munF
     */
    public String getMunF() {
        return munF;
    }

    /**
     * @param munF the munF to set
     */
    public void setMunF(String munF) {
        this.munF = munF;
    }

    /**
     * @return the locF
     */
    public String getLocF() {
        return locF;
    }

    /**
     * @param locF the locF to set
     */
    public void setLocF(String locF) {
        this.locF = locF;
    }

    /**
     * @return the cpF
     */
    public String getCpF() {
        return cpF;
    }

    /**
     * @param cpF the cpF to set
     */
    public void setCpF(String cpF) {
        this.cpF = cpF;
    }

    /**
     * @return the paisF
     */
    public String getPaisF() {
        return paisF;
    }

    /**
     * @param paisF the paisF to set
     */
    public void setPaisF(String paisF) {
        this.paisF = paisF;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the tel
     */
    public String getTel() {
        return tel;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * @return the cel
     */
    public String getCel() {
        return cel;
    }

    /**
     * @param cel the cel to set
     */
    public void setCel(String cel) {
        this.cel = cel;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the lic
     */
    public byte[] getLic() {
        return lic;
    }

    /**
     * @param lic the lic to set
     */
    public void setLic(byte[] lic) {
        this.lic = lic;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the noFac
     */
    public String getNoFac() {
        return noFac;
    }

    /**
     * @param noFac the noFac to set
     */
    public void setNoFac(String noFac) {
        this.noFac = noFac;
    }

    /**
     * @return the agte
     */
    public String getAgte() {
        return agte;
    }

    /**
     * @param agte the agte to set
     */
    public void setAgte(String agte) {
        this.agte = agte;
    }

    /**
     * @return the inicia
     */
    public Date getInicia() {
        return inicia;
    }

    /**
     * @param inicia the inicia to set
     */
    public void setInicia(Date inicia) {
        this.inicia = inicia;
    }

    /**
     * @return the vence
     */
    public Date getVence() {
        return vence;
    }

    /**
     * @param vence the vence to set
     */
    public void setVence(Date vence) {
        this.vence = vence;
    }

    /**
     * @return the pago
     */
    public int getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(int pago) {
        this.pago = pago;
    }

    /**
     * @return the tipoPago
     */
    public String getTipoPago() {
        return tipoPago;
    }

    /**
     * @param tipoPago the tipoPago to set
     */
    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    /**
     * @return the fPago
     */
    public Date getfPago() {
        return fPago;
    }

    /**
     * @param fPago the fPago to set
     */
    public void setfPago(Date fPago) {
        this.fPago = fPago;
    }

    /**
     * @return the importe
     */
    public double getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the folios
     */
    public int getFolios() {
        return folios;
    }

    /**
     * @param folios the folios to set
     */
    public void setFolios(int folios) {
        this.folios = folios;
    }

    /**
     * @return the fUsados
     */
    public int getfUsados() {
        return fUsados;
    }

    /**
     * @param fUsados the fUsados to set
     */
    public void setfUsados(int fUsados) {
        this.fUsados = fUsados;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
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