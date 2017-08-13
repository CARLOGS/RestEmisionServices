package fe.db.mongo;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.io.Serializable;
import java.util.ResourceBundle;

public class MongoDAO {

    private static DB db;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public MongoDAO() throws Exception {
        ResourceBundle rb = ResourceBundle.getBundle("fe.db.mongo.Mongo");
        String host = rb.getString("mongo.host");
        String port = rb.getString("mongo.port");
        String dataBase = rb.getString("mongo.db");
        String adminBase = rb.getString("mongo.admin.db");
        String mongoUser = rb.getString("mongo.user");
        String mongoPass = rb.getString("mongo.pass");
        
        if (db == null) {
            MongoClient mongoClient = new MongoClient(
                Arrays.asList(
                    new ServerAddress(host, port == null || "".equals(port) ? 27017 : Integer.parseInt(port))
                ),
                Arrays.asList(
                    MongoCredential.createMongoCRCredential(mongoUser,adminBase,mongoPass.toCharArray())
                )
            );
            db = mongoClient.getDB(dataBase);
        }
    }

    public void saveDoc(MongoDocument mdoc) throws Exception {
        DBCollection coll = db.getCollection(mdoc.getCollection());

        BasicDBObject doc = new BasicDBObject("uuid", mdoc.getUuid()).
                append("date", sdf.format(mdoc.getDate())).
                append("re", mdoc.getRe()).
                append("rr", mdoc.getRr()).
                append("zip", mdoc.isZip() ? "ZIP" : "PLAIN").
                append("xmlBytes", mdoc.getXml());

        if (mdoc.getPdf() != null && mdoc.getPdf().length > 0) {
            doc.append("pdfBytes", mdoc.getPdf());
        }
        if (mdoc.getId() != null && !"".equals(mdoc.getId())) {
            doc.append("id", mdoc.getId());
        }

        coll.insert(doc);
    }

    public MongoDocument readDocByUuid(String collection, String value) throws Exception {
        return readDoc(collection, "uuid", value);
    }

    public MongoDocument readDocById(String collection, String value) throws Exception {
        return readDoc(collection, "id", value);
    }

    private MongoDocument readDoc(String collection, String node, String value) throws Exception {
        MongoDocument mdoc = null;

        // Selecct criteria using cursor
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(node, value);

        BasicDBObject query = new BasicDBObject(map);
        DBCollection coll = db.getCollection(collection);

        DBCursor cursor = coll.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject bobj = (BasicDBObject) cursor.next();

                mdoc = new MongoDocument();
                mdoc.setCollection(collection);
                if (bobj.get("id") != null) {
                    mdoc.setId(bobj.get("id").toString());
                }
                mdoc.setDate(sdf.parse(bobj.get("date").toString()));
                mdoc.setUuid(bobj.get("uuid").toString());
                mdoc.setRe(bobj.get("re").toString());
                mdoc.setRr(bobj.get("rr").toString());
                mdoc.setZip("ZIP".equals(bobj.get("zip").toString()));
                mdoc.setXml((byte[]) bobj.get("xmlBytes"));
                if (bobj.get("pdfBytes") != null) {
                    mdoc.setPdf((byte[]) bobj.get("pdfBytes"));
                }
            }
        } finally {
            cursor.close();
        }

        return mdoc;
    }

    public void savePrefer(MongoPrefer mdoc) {
        DBCollection coll = db.getCollection(mdoc.getCollection());

        BasicDBObject doc = new BasicDBObject("id", mdoc.getId()).
            append("rfc", mdoc.getRfc()).
            append("serie", mdoc.getSerie()).
            append("tipoDocs", mdoc.getTipoDocumento()).
            append("metodoPago", mdoc.getMetodoPago()).
            append("numCta", mdoc.getNumCta()).
            append("moneda", mdoc.getMoneda()).
            append("regimen", mdoc.getRegimen()).
            append("lugarDeExp", mdoc.getLugarDeExpedicion()).
            append("condPago", mdoc.getCondicionesPago());
        BasicDBObject dImp = new BasicDBObject().
            append("ivaTras", mdoc.getIvaTras()).
            append("tipoIva", mdoc.getTipoIva()).
            append("ieps", mdoc.getIeps()).
            append("iepsTras", mdoc.getIepsTras()).
            append("ivaRet", mdoc.getIvaRet()).
            append("isrRet", mdoc.getIsrRet());
        doc.append("impuestos", dImp);

        BasicDBObject docOri = getDoc(mdoc.getCollection(), mdoc.getId());
        if ( docOri == null )
            coll.insert(doc);
        else
            coll.update(docOri, doc);
    }

    public MongoPrefer readPreferById(String collection, Long id) throws Exception {
        MongoPrefer mdoc = null;

        // Selecct criteria using cursor looking for using ID
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        BasicDBObject query = new BasicDBObject(map);
        DBCollection coll = db.getCollection(collection);

        DBCursor cursor = coll.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject bobj = (BasicDBObject) cursor.next();

                mdoc = new MongoPrefer();
                mdoc.setCollection(collection);
                if (bobj.get("id") != null) {
                    mdoc.setId(bobj.getLong("id"));
                }
                mdoc.setRfc(bobj.getString("rfc"));
                mdoc.setSerie(bobj.getString("serie"));
                mdoc.setTipoDocumento(bobj.getString("tipoDocs"));
                mdoc.setMetodoPago(bobj.getString("metodoPago"));
                mdoc.setNumCta(bobj.getString("numCta"));
                mdoc.setMoneda(bobj.getString("moneda"));
                mdoc.setRegimen(bobj.getString("regimen"));
                mdoc.setLugarDeExpedicion(bobj.getString("lugarDeExp"));
                mdoc.setCondicionesPago(bobj.getString("condPago"));
                // impuestos                
                BasicDBObject dImp = (BasicDBObject)bobj.get("impuestos");
                mdoc.setIvaTras(dImp.getInt("ivaTras") );
                mdoc.setTipoIva(dImp.getInt("tipoIva") );
                mdoc.setIeps( dImp.getInt("ieps") );
                mdoc.setIepsTras(dImp.getInt("iepsTras") );
                mdoc.setIvaRet(dImp.getInt("ivaRet") );
                mdoc.setIsrRet(dImp.getInt("isrRet") );
            }
        } finally {
            cursor.close();
        }

        return mdoc;
    }
    
    private BasicDBObject getDoc(String collection, Long id) {
        BasicDBObject bobj = null;

        // Selecct criteria using cursor
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);

        BasicDBObject query = new BasicDBObject(map);
        DBCollection coll = db.getCollection(collection);

        DBCursor cursor = coll.find(query);

        try {
            if (cursor.hasNext())
                bobj = (BasicDBObject) cursor.next();
        } finally {
            cursor.close();
        }

        return bobj;
    }

    
    public MongoDAO.MongoDocument getFromMongo(String mongoColl, String uuid) throws Exception {
        return readDocByUuid(mongoColl, uuid);
    }


    public Object getLogoMongo(String mongoColl, String rfc) throws Exception {
        return getLogoPersonal(mongoColl, rfc);
    }

    public Object getLogoPersonal(String collection, String rfc) {
        BasicDBObject bobj = null;

        // Selecct criteria using cursor
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("rfc", rfc);

        BasicDBObject query = new BasicDBObject(map);
        DBCollection coll = db.getCollection(collection);

        DBCursor cursor = coll.find(query);

        try {
            if (cursor.hasNext())
                bobj = (BasicDBObject) cursor.next();
        } finally {
            cursor.close();
        }

        return bobj == null ? null : bobj.get("imgBytes");
    }

    public static class MongoDocument implements Serializable {

        private String collection;
        private String id;
        private Date date;
        private String re;
        private String rr;
        private String uuid;
        private byte[] xml;
        private byte[] pdf;
        private boolean isZip;
        private Date fecha;
        private double importe;
        private String nombreEmisor;
        private String nombreReceptor;
        private String name;
        private String numeroFactura;
        private Long accesoId;
        private short origen = 1;
        private String moneda = "MXN";
        private double tipoCambio = 1d;
        private String rfcTercero;

        public MongoDocument() {
        }

        public MongoDocument(String collection, String id, Date date, String re, String rr, String uuid, byte[] xml, byte[] pdf, boolean isZip) {
            this.collection = collection;
            this.date = date;
            this.id = id;
            this.re = re;
            this.rr = rr;
            this.uuid = uuid;
            this.xml = xml;
            this.pdf = pdf;
            this.isZip = isZip;
        }

        public Date getFecha() {
            return fecha;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        public String getNombreEmisor() {
            return nombreEmisor;
        }

        public void setNombreEmisor(String nombreEmisor) {
            this.nombreEmisor = nombreEmisor;
        }

        public String getNombreReceptor() {
            return nombreReceptor;
        }

        public void setNombreReceptor(String nombreReceptor) {
            this.nombreReceptor = nombreReceptor;
        }

        public double getImporte() {
            return importe;
        }

        public void setImporte(double importe) {
            this.importe = importe;
        }

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getRe() {
            return re;
        }

        public void setRe(String re) {
            this.re = re;
        }

        public String getRr() {
            return rr;
        }

        public void setRr(String rr) {
            this.rr = rr;
        }

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public byte[] getXml() {
            return xml;
        }

        public void setXml(byte[] xml) {
            this.xml = xml;
        }

        public byte[] getPdf() {
            return pdf;
        }

        public void setPdf(byte[] pdf) {
            this.pdf = pdf;
        }

        public boolean isZip() {
            return isZip;
        }

        public void setZip(boolean isZip) {
            this.isZip = isZip;
        }

        /**
         * @return the accesoId
         */
        public Long getAccesoId() {
            return accesoId;
        }

        /**
         * @param accesoId the accesoId to set
         */
        public void setAccesoId(Long accesoId) {
            this.accesoId = accesoId;
        }

        public short getOrigen() {
            return origen;
        }

        public void setOrigen(short origen) {
            this.origen = origen;
        }

        public String getNumeroFactura() {
            return numeroFactura;
        }

        public void setNumeroFactura(String numeroFactura) {
            this.numeroFactura = numeroFactura;
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

    public static class MongoPrefer implements Serializable {

        private long id;
        private String collection = "";
        private String rfc = "";
        private String serie = "";
        private String tipoDocumento = "";
        private String moneda = "";
        private String regimen = "";
        private int ivaTras = 0;
        private int tipoIva = 0;
        private int iepsTras = 0;
        private double ieps = 0.0;
        private int ivaRet = 0;
        private int isrRet = 0;
        private String metodoPago = "";
        private String numCta = "";
        private String lugarDeExpedicion = "";
        private String condicionesPago;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getRfc() {
            return rfc;
        }

        public void setRfc(String rfc) {
            this.rfc = rfc;
        }

        public String getSerie() {
            return serie;
        }

        public void setSerie(String serie) {
            this.serie = serie;
        }

        public String getTipoDocumento() {
            return tipoDocumento;
        }

        public void setTipoDocumento(String tipoDocumento) {
            this.tipoDocumento = tipoDocumento;
        }

        public String getMoneda() {
            return moneda;
        }

        public void setMoneda(String moneda) {
            this.moneda = moneda;
        }

        public String getRegimen() {
            return regimen;
        }

        public void setRegimen(String regimen) {
            this.regimen = regimen;
        }

        public int getIvaTras() {
            return ivaTras;
        }

        public void setIvaTras(int ivaTras) {
            this.ivaTras = ivaTras;
        }

        public int getTipoIva() {
            return tipoIva;
        }

        public void setTipoIva(int tipoIva) {
            this.tipoIva = tipoIva;
        }

        public int getIepsTras() {
            return iepsTras;
        }

        public void setIepsTras(int iepsTras) {
            this.iepsTras = iepsTras;
        }

        public double getIeps() {
            return ieps;
        }

        public void setIeps(double ieps) {
            this.ieps = ieps;
        }

        public int getIvaRet() {
            return ivaRet;
        }

        public void setIvaRet(int ivaRet) {
            this.ivaRet = ivaRet;
        }

        public int getIsrRet() {
            return isrRet;
        }

        public void setIsrRet(int isrRet) {
            this.isrRet = isrRet;
        }

        public String getMetodoPago() {
            return metodoPago;
        }

        public void setMetodoPago(String metodoPago) {
            this.metodoPago = metodoPago;
        }

        public String getNumCta() {
            return numCta;
        }

        public void setNumCta(String numCta) {
            this.numCta = numCta;
        }

        public String getLugarDeExpedicion() {
            return lugarDeExpedicion;
        }

        public void setLugarDeExpedicion(String lugarDeExpedicion) {
            this.lugarDeExpedicion = lugarDeExpedicion;
        }

        public String getCondicionesPago() {
            return condicionesPago;
        }

        public void setCondicionesPago(String condicionesPago) {
            this.condicionesPago = condicionesPago;
        }
    }
}
