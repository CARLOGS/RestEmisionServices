/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itsb.ws.rest;

import fe.db.mongo.MongoRecepcionDAO;
import fe.db.mongo.XmlData;
import fe.db.recepcion.RCfdi;
import fe.db.recepcion.RCfdiNomina;
import fe.db.recepcion.RCfdiPagos;
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
    
    @GET
    @Path("xml")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response getMongoRecXml(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                MongoRecepcionDAO mdao = new MongoRecepcionDAO();
                
                if ( uuid != null && !uuid.isEmpty())  {
                    RecepcionDao  rdao = new RecepcionDao();
                    RCfdi cfdi = rdao.getCfdi(uuid);
                    
                    // Recupera xml bytes
                    byte[] bytes = null;
                    if ( cfdi != null )
                        bytes = mdao.getXmlBytes(uuid, cfdi.getMongoCollection());
                    
                    return Response.ok().entity(bytes).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }
    @GET
    @Path("pdf")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response getMongoRecPdf(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                MongoRecepcionDAO mdao = new MongoRecepcionDAO();
                
                if ( uuid != null && !uuid.isEmpty())  {
                    RecepcionDao  rdao = new RecepcionDao();
                    RCfdi cfdi = rdao.getCfdi(uuid);
                    
                    // Recupera pdf bytes
                    byte[] bytes = null;
                    if ( cfdi != null )
                        bytes = mdao.getPdfBytes(
                            MongoRecepcionDAO.TIPO_DOC.CFDI, 
                            cfdi.getMongoCollection(),
                            cfdi.getEstadoDocumento(),
                            cfdi.getMoneda(),
                            "T".equals(cfdi.getTipoComprobante())
                            ? "TRASLADO"
                            : "E".equals(cfdi.getTipoComprobante())
                            ? "NOTA DE CREDITO"
                            :"FACTURA",
                            cfdi.getUuid()
                        );
                    
                    return Response.ok().entity(bytes).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }

    @GET
    @Path("pago/xml")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response getMongoRecPagoXml(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                MongoRecepcionDAO mdao = new MongoRecepcionDAO();
                
                if ( uuid != null && !uuid.isEmpty())  {
                    RecepcionDao  rdao = new RecepcionDao();
                    RCfdiPagos cfdi = rdao.getCfdiPagos(uuid);
                    
                    // Recupera xml bytes
                    byte[] bytes = null;
                    if ( cfdi != null )
                        bytes = mdao.getXmlBytes(uuid, cfdi.getMongoCollection());
                    
                    return Response.ok().entity(bytes).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }
    @GET
    @Path("pago/pdf")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response getMongoRecPagoPdf(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                MongoRecepcionDAO mdao = new MongoRecepcionDAO();
                
                if ( uuid != null && !uuid.isEmpty())  {
                    RecepcionDao  rdao = new RecepcionDao();
                    RCfdiPagos cfdi = rdao.getCfdiPagos(uuid);
                    
                    // Recupera pdf bytes
                    byte[] bytes = null;
                    if ( cfdi != null )
                        bytes = mdao.getPdfBytes(
                            MongoRecepcionDAO.TIPO_DOC.PAGO, 
                            cfdi.getMongoCollection(),
                            cfdi.getEstadoDocumento(),
                            cfdi.getMoneda(),
                            "PAGO",
                            cfdi.getUuid()
                        );
                    
                    return Response.ok().entity(bytes).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }

    @GET
    @Path("nomina/xml")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response getMongoRecNominaXml(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                MongoRecepcionDAO mdao = new MongoRecepcionDAO();
                
                if ( uuid != null && !uuid.isEmpty())  {
                    RecepcionDao  rdao = new RecepcionDao();
                    RCfdi cfdi = rdao.getCfdi(uuid);
                    
                    // Recupera xml bytes
                    byte[] bytes = mdao.getXmlBytes(uuid, cfdi.getMongoCollection());
                    
                    return Response.ok().entity(bytes).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }
    @GET
    @Path("nomina/pdf")
    @Produces({MediaType.APPLICATION_OCTET_STREAM})
    public Response getMongoRecNominaPdf(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                MongoRecepcionDAO mdao = new MongoRecepcionDAO();
                
                if ( uuid != null && !uuid.isEmpty())  {
                    RecepcionDao  rdao = new RecepcionDao();
                    RCfdiNomina cfdi = rdao.getCfdiNomina(uuid);
                    
                    // Recupera pdf bytes
                    byte[] bytes = null;
                    if ( cfdi != null )
                        bytes = mdao.getPdfBytes(
                            MongoRecepcionDAO.TIPO_DOC.NOMINA, 
                            cfdi.getMongoCollection(),
                            cfdi.getEstadoDocumento(),
                            "MXN",
                            "NOMINA",
                            cfdi.getUuid()
                        );
                    
                    return Response.ok().entity(bytes).build();
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
                MongoRecepcionDAO mdao = new MongoRecepcionDAO();
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
