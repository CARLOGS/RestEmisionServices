/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itsb.ws.rest;

import fe.db.mongo.MongoRecepcionDAO;
import fe.db.mongo.XmlData;
import fe.db.recepcion.MCfdi;
import fe.db.recepcion.RecepcionDao;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author CarloGS
 */
@Path("recepcion/{uuid}") //Especificamos una ruta que se debe usar para invocar este método y un parámetro (tipo)
public class MongoRecepcion {
    private MongoRecepcionDAO mdao;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMongoRec(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoRecepcionDAO();
                
                if ( uuid != null && !uuid.isEmpty())  {
                    RecepcionDao  rdao = new RecepcionDao();
                    MCfdi cfdi = rdao.getCfdi(uuid);
                    
                    // Almacena Xml
                    XmlData xdata = mdao.getXmlRecepcion(uuid, cfdi);
                    
                    return Response.ok().entity(xdata).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response saveMongoRec(@PathParam("uuid") String uuid, XmlData xdata, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoRecepcionDAO();
                if ( xdata.getRfcEmisor() != null && !xdata.getRfcEmisor().isEmpty())  {
                    
                    // Almacena Xml
                    mdao.setXmlRecepcion(xdata);
                    
                    return Response.ok().entity("Ok").build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }
}
