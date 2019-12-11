/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import sb.reports.ReportUtils;

/**
 *
 * @author CarloGS
 */
public class MongoRecepcionDAO {
    private static DB db;
    public static enum TIPO_DOC {CFDI,PAGO,NOMINA};

    public MongoRecepcionDAO() throws Exception {
        if (db == null) {
            ResourceBundle rb = ResourceBundle.getBundle("fe.db.mongo.Mongo");

            String host = rb.getString("r.mongo.host");
            String port = rb.getString("r.mongo.port");
            String dataBase = rb.getString("r.mongo.db");
            String adminBase = rb.getString("r.mongo.admin.db");
            String mongoUser = rb.getString("r.mongo.user");
            String mongoPass = rb.getString("r.mongo.pass");
        
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

    public void insertDoc(BasicDBObject mdoc, String collection) throws Exception {
        DBCollection coll = db.getCollection(collection);

        coll.insert(mdoc);
    }

    public void setXmlRecepcion(XmlData xdata) throws Exception {
        
        // Antrior
        BasicDBObject aobj = getDBObject(xdata.getColl(), "uuid", xdata.getUuid());
        
        // Nuevo
        BasicDBObject nobj= new BasicDBObject("date", xdata.getFecha())
            .append("uuid", xdata.getUuid())
            .append("re", xdata.getRfcEmisor())
            .append("rr", xdata.getRfcReceptor())
            .append("zip", "zip");

        if ( xdata.getXml() != null )
            nobj.append(
                "xmlBytes", 
                MongoUtils.getZipCipher( xdata.getXml(), "xml" )
            );
        
        if ( xdata.getPdf() != null )
            nobj.append(
                "pdfBytes", 
                MongoUtils.getZipCipher( xdata.getPdf(), "xmlp" )
            );
        
        DBCollection coll = db.getCollection(xdata.getColl());
        
        // Hace insert o update
        if ( aobj == null )
            coll.insert(nobj);
        else
            coll.update(aobj, nobj);
    }

    // Recupera un JSon con el criterio { node : value }
    private BasicDBObject getDBObject(String collection, String node, String value) throws Exception {
        BasicDBObject obj = null;
        
        // Select criteria using cursor
        HashMap<String, String> map = new HashMap<String, String>();
        map.put(node, value);

        BasicDBObject query = new BasicDBObject(map);
        DBCollection coll = db.getCollection(collection);

        DBCursor cursor = coll.find(query);

        try {
            
            if (cursor.hasNext())
                obj = (BasicDBObject) cursor.next();

        } finally {
            cursor.close();
        }
        
        return obj;
    }

    public XmlData getXmlRecepcion(String uuid, String collection) {
        XmlData xdata = null;
        
        // Selecct criteria using cursor
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("uuid", uuid);

        BasicDBObject query = new BasicDBObject(map);
        DBCollection coll = db.getCollection(collection);

        DBCursor cursor = coll.find(query);

        try {
            if (cursor.hasNext()) {
                BasicDBObject bobj = (BasicDBObject) cursor.next();

                xdata = new XmlData();
                xdata.setColl(collection);
//                xdata.setFecha((Date)bobj.get("date"));
                xdata.setUuid(bobj.getString("uuid"));
                xdata.setRfcEmisor(bobj.getString("re"));
                xdata.setRfcReceptor(bobj.getString("rr"));
                
                byte[] objx = (byte[])bobj.get("xmlBytes");
                if (objx != null) {
                    xdata.setXml(objx);
                }
                
                byte[] objp = (byte[])bobj.get("pdfBytes");
                if (objp != null) {
                    xdata.setPdf(objp);
                }
            }
        } finally {
            cursor.close();
        }

        return xdata;
    }

    public byte[] getXmlBytes(String uuid, String collection) {
        byte[] bytes = null;
        
        try {
            XmlData xdata = getXmlRecepcion(uuid, collection);
            bytes = ReportUtils.getCrypUnZip(xdata.getXml(), "sebh12#");
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
        
        return bytes;
    }

    public byte[] getPdfBytes(TIPO_DOC tipo, String collection, int estadoDoc, String moneda, String tipoComp, String uuid) {
        byte[] bytes = null;
        
        try {
            XmlData xdata = getXmlRecepcion(uuid, collection);
            ReportUtils rutils = new ReportUtils();
            
            if ( xdata.getXml() != null ) {
                bytes = rutils.getCrypUnZip(xdata.getXml(), "sebh12#");
                bytes = rutils.getPdfRecibidos(
                    bytes, 
                    tipo.equals(TIPO_DOC.CFDI) 
                        ? rutils.getDefRepRecibidos()
                        : tipo.equals(TIPO_DOC.PAGO)
                        ? rutils.getDefRepPagRecibidos()
                        : tipo.equals(TIPO_DOC.NOMINA)
                        ? rutils.getDefRepNomRecibidos()
                        : rutils.getDefRepRecibidos(),
                    estadoDoc == 0, 
                    false, 
                    null, 
                    moneda, 
                    tipoComp,
                    tipo.equals(TIPO_DOC.CFDI) 
                        ? "/Comprobante/Conceptos/Concepto"
                        : tipo.equals(TIPO_DOC.PAGO) 
                        ? "/Comprobante/Complemento/Pagos/Pago"
                        : tipo.equals(TIPO_DOC.NOMINA)
                        ? "/Comprobante/Conceptos/Concepto"
                        : "/Comprobante/Conceptos/Concepto",
                    false
                );
            }
        } catch(Exception ex) {
            ex.printStackTrace(System.err);
        }
        
        return bytes;
    }
}
