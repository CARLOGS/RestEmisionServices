package mx.com.itsb.ws.rest; //Esta es la estructura de paquete creada

import fe.db.emision.DCfdi;
import fe.db.emision.DecDao;
import fe.db.licenses.LicenciasDao;
import fe.db.mongo.dec.MongoDAO;
import fe.db.pagos.EPagos;
import fe.db.pagos.EPagosBorrador;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*; //Importamos la librería para manejar RESTful
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.bouncycastle.util.encoders.Base64;
import sb.reports.ReportUtils;

@Path("getDXml/{uuid}") //Especificamos una ruta que se debe usar para invocar este método y un parámetro (tipo)
public class MongoRetrieveDec {
    private final DecDao edao = new DecDao();
    private final LicenciasDao ldao = new LicenciasDao();
    private MongoDAO mdao;

    public MongoRetrieveDec() {}
    
    @GET
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getXml(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    DCfdi cfdi = edao.getCfdiUuid(uuid);
                    if ( cfdi != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getFromMongo(cfdi.getMongoCollection(), uuid);
                        if (mdoc != null)
                            return ReportUtils.getCrypUnZip(mdoc.getXml(), "sebh12#");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("xmlPago") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getXmlPago(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    EPagos epago = edao.getPagosUuid(uuid);
                    if ( epago != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getFromMongo(epago.getMongoCollection(), uuid);
                        if (mdoc != null)
                            return ReportUtils.getCrypUnZip(mdoc.getXml(), "sebh12#");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("xmlp") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getXmlP(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    DCfdi cfdi = edao.getCfdiUuid(uuid);
                    if ( cfdi != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getFromMongo(cfdi.getMongoCollection(), uuid);
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
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("xmlPPago") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getXmlPPago(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    EPagos epago = edao.getPagosUuid(uuid);
                    if ( epago != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getFromMongo(epago.getMongoCollection(), uuid);
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
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("xmlPPagoBorrador") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getXmlPPagoBorrador(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    EPagosBorrador epago = edao.getPagosBorradorUuid(uuid);
                    if ( epago != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getFromMongo(epago.getMongoCollection(), uuid);
                        if (mdoc  != null)
                            return new ReportUtils().getUnZip(Base64.decode(mdoc.getPdf()));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("acuse") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getAcuse(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    DCfdi cfdi = edao.getCfdiUuid(uuid);
                    if ( cfdi != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getAcuseFromMongo(cfdi.getMongoCollection(), uuid);
                        if (mdoc  != null)
                            return ReportUtils.getCrypUnZip(mdoc.getXml(), "sebh12#");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("acusePago") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getAcusePago(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    EPagos epago = edao.getPagosUuid(uuid);
                    if ( epago != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getAcuseFromMongo(epago.getMongoCollection(), uuid);
                        if (mdoc  != null)
                            return ReportUtils.getCrypUnZip(mdoc.getXml(), "sebh12#");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("pdf") // Regresa el PDF usando el XML de impresión
    @Produces({"application/pdf"})
    public byte[] getPdf(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if (uuid != null && !uuid.isEmpty()) {
                    DCfdi cfdi = edao.getCfdiUuid(uuid);
                    if ( cfdi != null )  {
                        byte[] bytes = getXmlP(uuid, httpRequest);
                        if (bytes  != null && bytes.length > 1024) {
                            bytes = new ReportUtils().getPdf(
                                bytes,
                                ldao.getPlantillaById(cfdi.getItems()),
                                cfdi.getEstadoDocumento() == 0, // Cancelado
                                cfdi.getModo() == 0, // Vista Previa si se generó en modo Desarrollo
                                (byte[])mdao.getLogoMongo("DatosPersonales", cfdi.getClientes().getRfc()),
                                cfdi.getMoneda(),
                                cfdi.getTipoDocumento()
                            );
                            return bytes;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("pdfPago") // Regresa el PDF usando el XML de impresión
    @Produces({"application/pdf"})
    public byte[] getPdfPago(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if (uuid != null && !uuid.isEmpty()) {
                    EPagos epago = edao.getPagosUuid(uuid);
                    if ( epago != null )  {
                        byte[] bytes = getXmlPPago(uuid, httpRequest);
                        if (bytes  != null && bytes.length > 1024) {
                            bytes = new ReportUtils().getPdfPago(
                                bytes,
                                ldao.getPlantillaById(epago.getItems()),
                                epago.getEstadoDocumento() == 0, // Cancelado
                                epago.getModo() == 0, // Vista Previa si se generó en modo Desarrollo
                                (byte[])mdao.getLogoMongo("DatosPersonales", epago.getClientes().getRfc()),
                                epago.getMoneda(),
                                "PAGO"
                            );
                            return bytes;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("pdfPagoBorrador") // Regresa el PDF usando el XML de impresión
    @Produces({"application/pdf"})
    public byte[] getPdfPagoBorrador(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if (uuid != null && !uuid.isEmpty()) {
                    EPagosBorrador epago = edao.getPagosBorradorUuid(uuid);
                    if ( epago != null )  {
                        byte[] bytes = getXmlPPagoBorrador(uuid, httpRequest);
                        if (bytes  != null && bytes.length > 1024) {
                            bytes = new ReportUtils().getPdfPago(
                                bytes,
                                ldao.getPlantillaById(epago.getItems()),
                                epago.getEstadoDocumento() == 0, // Cancelado
                                true, // Vista Previa si se generó en modo Desarrollo
                                (byte[])mdao.getLogoMongo("DatosPersonales", epago.getClientes().getRfc()),
                                epago.getMoneda(),
                                "PAGO"
                            );
                            return bytes;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
        
        return ("<Error msg=\"Documento no encontrado [" + uuid + "]\" />").getBytes();
    }
    
    @GET
    @Path("zip") // Regresa un zip con el XML y PDF del UUID solicitado
    @Produces({MediaType.APPLICATION_OCTET_STREAM}) // "application/octet-stream"
    public byte[] getZip(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] pdf = getPdf(uuid, httpRequest);
                byte[] xml = getXml(uuid, httpRequest);

                if ( pdf.length < 2014 )
                    return pdf;
                if ( xml.length < 1024 )
                    return xml;

                // PDF
                ZipOutputStream zos = new ZipOutputStream(baos);
                zos.putNextEntry(new ZipEntry(uuid + ".pdf"));
                zos.write(pdf);
                zos.closeEntry();

                // XML
                zos.putNextEntry(new ZipEntry(uuid + ".xml"));
                zos.write(xml);
                zos.closeEntry();

                zos.flush();
                zos.close();

                return baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
    }
    
    @GET
    @Path("zipPago") // Regresa un zip con el XML y PDF del UUID solicitado
    @Produces({MediaType.APPLICATION_OCTET_STREAM}) // "application/octet-stream"
    public byte[] getZipPago(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] pdf = getPdfPago(uuid, httpRequest);
                byte[] xml = getXmlPago(uuid, httpRequest);

                if ( pdf.length < 2014 )
                    return pdf;
                if ( xml.length < 1024 )
                    return xml;

                // PDF
                ZipOutputStream zos = new ZipOutputStream(baos);
                zos.putNextEntry(new ZipEntry(uuid + ".pdf"));
                zos.write(pdf);
                zos.closeEntry();

                // XML
                zos.putNextEntry(new ZipEntry(uuid + ".xml"));
                zos.write(xml);
                zos.closeEntry();

                zos.flush();
                zos.close();

                return baos.toByteArray();
            } catch (Exception e) {
                e.printStackTrace(System.out);

                return ("<Error msg=\"" + e.getMessage() + "\" />").getBytes();
            }
        else
            return ("<Error msg=\"IP no autorizada\" />").getBytes();
    }
    
    @Provider
    public class DebugExceptionMapper implements ExceptionMapper<Exception> {

        @Override
        public Response toResponse(Exception exception) {
            exception.printStackTrace(System.out);
            return Response.serverError().entity(exception.getMessage()).build();
        } 
    }
}
