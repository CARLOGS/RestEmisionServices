/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itsb.ws.rest;

import fe.db.emision.EAutoCfdi;
import fe.db.emision.EmisionDao;
import fe.db.mongo.MongoDAO;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import sb.reports.ReportUtils;

/**
 *
 * @author CarloGS
 */
@Path("auto/{ref}")
public class MongoAutoRetrieve {
    private final SimpleDateFormat sdf = new SimpleDateFormat("YYYYMM");
    private MongoDAO mdao;
    private final EmisionDao edao = new EmisionDao();
    
    @GET
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getAutoXmlP(@PathParam("ref") String ref, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (ref != null && !ref.isEmpty()) {
                    EAutoCfdi auto = edao.getAutoCfdiRef(ref);
                    if ( auto != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getFromMongo("A" + getCollection(auto.getFecha()), ref);
                        if (mdoc  != null)
                            return ReportUtils.getCrypUnZip(mdoc.getPdf(), "sebh12#");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + ref + "]\" />").getBytes();
    }

    private String getCollection(Date fecha) {
        return sdf.format(fecha);
    }
    
}
