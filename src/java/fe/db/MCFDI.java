/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fe.db;

/**
 *
 * @author pedro
 */



import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Index;

@Entity
@Table(name = "E_CFDI")
public class MCFDI implements Serializable {
    
    public MCFDI(){
        super();
    }
    public MCFDI( String rfc, String nombre, String uuid, Date fecha,
            Date fecharecibido, double importe, Integer addendaid, Long clientesid,
            Long proveedoresid, int estadodocumento, int modo, String mongocollection,
            String mongoip, String numerofactura, Long estatuswf ){
        super();
        this.rfc = rfc;
        this.nombre = nombre;
        this.uuid = uuid;
        this.fecha = fecha;
        this.fecharecibido = fecharecibido;
        this.importe = importe;
        this.addendaid = addendaid;
        this.clientesid = clientesid;
        this.proveedoresid = proveedoresid;
        this.estadodocumento = estadodocumento;
        this.modo = modo;
        this.mongocollection = mongocollection;
        this.mongoip = mongoip;
        this.numerofactura = numerofactura;
        this.estatuswf = estatuswf;
        
    }

    private static final long serialVersionUID = 1L;    
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    @Column(name="RFC", nullable=true, length=13)
    private String rfc = "";
    
    @Column(name="NOMBRE", nullable=true, length=300)
    private String nombre = "";
    
    @Column(name="UUID", nullable=true, length=45)
    private String uuid = "";
         
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="FECHA", nullable=true)
    private Date fecha = new Date();
    
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="FECHA_RECIBIDO", nullable=true)
    private Date fecharecibido = new Date();
        
    @Column(name = "IMPORTE", nullable = false)
    private double importe=0;
    
    @Column(name="ADDENDAS_ID", nullable=true)
    private Integer addendaid;
    
    @Column(name="clientes_ID", nullable=true)
    private Long clientesid = 0l;
    
    @Column(name="proveedores_ID", nullable=true)
    private Long proveedoresid = 0l;
    
    @Column(name="acceso_ID", nullable=true)
    private Long accesoid = 0l;
    
    @Column(name="ESTADO_DOCUMENTO", nullable=true)
    private int estadodocumento = 0;
    
    @Column(name="MODO", nullable=true)
    private int modo = 0;
    
    @Column(name="MONGO_COLLECTION", nullable=true, length=10)
    private String mongocollection = "";
    
    @Column(name="MONGO_IP", nullable=true, length=100)
    private String mongoip = "";
    
    @Column(name="NUMERO_FACTURA", nullable=true, length=31)
    private String numerofactura = "";
    
    @Column(name="ESTATUS_WF", nullable=true)
    private Long estatuswf;
    
    @Column(name="RRFC", nullable=true, length=13)
    private String rRfc = "";
    
    @Column(name="RNOMBRE", nullable=true, length=300)
    private String rNombre = "";

    @Column(name="ORIGEN", nullable=false)
    private int origen = 1;
    
    @Column(name="ADJUNTO", nullable=false)
    private int adjunto = 0;
    
    @Column(name="MONEDA", nullable=false)
    private String moneda = "MXN";
    
    @Column(name="TIPO_CAMBIO", nullable=false)
    private double tipoCambio = 1d;
    
    @Index(name = "CFDI_TERCERO_IDX")
    @Column(name = "RFC_TERCERO", nullable = true, length = 13)
	private String rfcTercero;
    
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

    public Date getFecharecibido() {
        return fecharecibido;
    }

    public void setFecharecibido(Date fecharecibido) {
        this.fecharecibido = fecharecibido;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Integer getAddendaid() {
        return addendaid;
    }

    public void setAddendaid(Integer addendaid) {
        this.addendaid = addendaid;
    }

    public Long getClientesid() {
        return clientesid;
    }

    public void setClientesid(Long clientesid) {
        this.clientesid = clientesid;
    }

    public Long getProveedoresid() {
        return proveedoresid;
    }

    public void setProveedoresid(Long proveedoresid) {
        this.proveedoresid = proveedoresid;
    }

    public int getEstadodocumento() {
        return estadodocumento;
    }

    public void setEstadodocumento(int estadodocumento) {
        this.estadodocumento = estadodocumento;
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public String getMongocollection() {
        return mongocollection;
    }

    public void setMongocollection(String mongocollection) {
        this.mongocollection = mongocollection;
    }

    public String getMongoip() {
        return mongoip;
    }

    public void setMongoip(String mongoip) {
        this.mongoip = mongoip;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public Long getEstatuswf() {
        return estatuswf;
    }

    public void setEstatuswf(Long estatuswf) {
        this.estatuswf = estatuswf;
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

    public Long getAccesoid() {
        return accesoid;
    }

    public void setAccesoid(Long accesoid) {
        this.accesoid = accesoid;
    }

    public int getOrigen() {
        return origen;
    }

    public void setOrigen(int origen) {
        this.origen = origen;
    }

    public int getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(int adjunto) {
        this.adjunto = adjunto;
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
}
