/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fe.db.mongo.dec;

import com.mongodb.BasicDBObject;
import java.text.SimpleDateFormat;
import org.bouncycastle.util.encoders.Base64;
import org.json.JSONObject;

/**
 *
 * @author CarloGS
 * 
 * {
 *      "rfce" : "AAA010101AAA",
 *      "rfcr" : "XAXX010101000",
 *      "uuid" : "AAA010101AAA-190101-120001.001",
 *      "fecha" : "2019-01-01T12:00:01",
 *      "ip" : "",
 *      "colleccion" : "M201901",
 *      "xml" : "A/2SDF3 ... 4GDFG==",
 *      "xmlp" : "PA/F3S ... 4GDYY=="
 * }
 */
public class MongoJsonDao {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public JSONObject saveXml(JSONObject json) {
        JSONObject jresp = null;

        try {
            MongoDAO mdao = new MongoDAO();
            BasicDBObject mdoc = new BasicDBObject("uuid", json.getString("uuid")).
                    append("date", json.getString("fecha")).
                    append("re", json.getString("rfce")).
                    append("rr", json.getString("rfcr")).
                    append("zip", true ? "ZIP" : "PLAIN").
                    append("xmlBytes", Base64.decode(json.getString("xml"))).
                    append("pdfBytes", Base64.decode(json.getString("xmlp")));

            mdao.insertDoc(mdoc, json.getString("coleccion"));

            jresp = new JSONObject().put("codigo", 200);
        } catch (Exception e) {
            try { 
                jresp = new JSONObject().put("codigo", 400);
                jresp.put("mensaje", e.getMessage()); 
            } catch(Exception ex) {}
            e.printStackTrace(System.err);
        }
        
        return jresp;
    }    

    public JSONObject saveCfdi(JSONObject json) {
        JSONObject jresp = null;
        
        try {
            MongoDAO mdao = new MongoDAO();

            BasicDBObject doc = new BasicDBObject("uuid", json.getString("uuid")).
                append("date", json.getString("fecha")).
                append("re", json.getString("rfcEmisor")).
                append("rr", json.getString("rfcReceptor")).
                append("zip", true ? "ZIP" : "PLAIN").
                append("xmlBytes", json.getString("xmlBytes"));

            if ( json.getString("pdfBytes") != null )
                doc.append("pdfBytes", json.getString("pdfBytes"));
            if ( json.getString("id") != null )
                doc.append("id", json.getString("id"));

            mdao.insertDoc(doc, json.getString("coleccion"));

            jresp = new JSONObject().put("codigo", 200);
        } catch (Exception e) {
            try { 
                jresp = new JSONObject().put("codigo", 400);
                jresp.put("mensaje", e.getMessage()); 
            } catch(Exception ex) {}
            e.printStackTrace(System.err);
        }
        
        return jresp;
    }

    public JSONObject savePrefer(JSONObject json) {
        JSONObject jresp = null;
        
        try {
            MongoDAO mdao = new MongoDAO();
            
            BasicDBObject doc = new BasicDBObject("id", json.getString("id")).
                append("rfc", json.getString("rfc")).
                append("serie", json.getString("serie")).
                append("tipoDocs", json.getString("tipoDocs")).
                append("metodoPago", json.getString("metodoPago")).
                append("numCta", json.getString("numCta")).
                append("moneda", json.getString("moneda")).
                append("regimen", json.getString("regimen")).
                append("lugarDeExp", json.getString("lugarDeExp")).
                append("condPago", json.getString("condPago"));
            BasicDBObject dImp = new BasicDBObject().
                append("ivaTras", json.getString("ivaTras")).
                append("tipoIva", json.getString("tipoIva")).
                append("ieps", json.getString("ieps")).
                append("iepsTras", json.getString("iepsTras")).
                append("ivaRet", json.getString("ivaRet")).
                append("isrRet", json.getString("isrRet"));
            doc.append("impuestos", dImp);

            // Hace un Update en lugar de Insert
            mdao.updateDoc(doc, json.getString("coleccion"));

            jresp = new JSONObject().put("codigo", 200);
        } catch (Exception e) {
            try { 
                jresp = new JSONObject().put("codigo", 400);
                jresp.put("mensaje", e.getMessage()); 
            } catch(Exception ex) {}
            e.printStackTrace(System.err);
        }
        
        return jresp;
    }

    public JSONObject getXml(String uuid, String collection) {
        JSONObject jresp = null;
        
        try {
            MongoDAO mdao = new MongoDAO();
            MongoDAO.MongoDocument mdoc = mdao.readDocByUuid(collection, uuid);
            
            if ( mdoc != null ) {
                jresp = new JSONObject();
                jresp.put("uuid", mdoc.getUuid());
                jresp.put("rfce", mdoc.getRe());
                jresp.put("rfcr", mdoc.getRr());
                jresp.put("xml", new String(mdoc.getXml()));
                jresp.put("pdf", new String(mdoc.getPdf()));
            }
        } catch (Exception e) {
            try { 
                jresp = new JSONObject().put("codigo", 400);
                jresp.put("mensaje", e.getMessage());
            } catch(Exception ex) {}
            e.printStackTrace(System.err);
        }
        
        return jresp;
    }

    public JSONObject delXml(String uuid, String coleccion) {
        JSONObject jresp = null;
        
        try {
            MongoDAO mdao = new MongoDAO();

            mdao.delDoc(uuid, coleccion);

            jresp = new JSONObject().put("codigo", 200);
        } catch (Exception e) {
            try { 
                jresp = new JSONObject().put("codigo", 400);
                jresp.put("mensaje", e.getMessage()); 
            } catch(Exception ex) {}
            e.printStackTrace(System.err);
        }
        
        return jresp;
    }
}
