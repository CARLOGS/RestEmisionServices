package mx.com.itsb.ws.rest; //Esta es la estructura de paquete creada

import fe.db.emision.ECfdi;
import fe.db.emision.EmisionDao;
import fe.db.licenses.LicenciasDao;
import fe.db.mongo.MongoDAO;
import fe.db.pagos.EPagos;
import fe.db.pagos.EPagosBorrador;
import fe.db.retenciones.ERetenciones;
import fe.pki.PKI;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*; //Importamos la librería para manejar RESTful
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import sb.reports.ReportUtils;

@Path("getXml/{uuid}") //Especificamos una ruta que se debe usar para invocar este método y un parámetro (tipo)
public class MongoRetrieve {
    private final EmisionDao edao = new EmisionDao();
    private final LicenciasDao ldao = new LicenciasDao();
    private MongoDAO mdao;

    public MongoRetrieve() {}
    
    @GET
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getXml(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    ECfdi cfdi = edao.getCfdiUuid(uuid);
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
    @Path("xmlRets") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getXmlRets(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    ERetenciones erets = edao.getRetsUuid(uuid);
                    if ( erets != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getFromMongo(erets.getMongoCollection(), uuid);
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
                    ECfdi cfdi = edao.getCfdiUuid(uuid);
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
                        if (mdoc  != null) {
                            byte[] bytes = new PKI().cipher_bytes(mdoc.getPdf(), "sebh12#", "Blowfish", Cipher.DECRYPT_MODE);
                            return new ReportUtils().getUnZip(bytes);
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
    @Path("xmlPRets") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getXmlPRets(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    ERetenciones erets = edao.getRetsUuid(uuid);
                    if ( erets != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getFromMongo(erets.getMongoCollection(), uuid);
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
    @Path("acuse") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getAcuse(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    ECfdi cfdi = edao.getCfdiUuid(uuid);
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
    @Path("acuseRets") // Regresa el XMl de impresión
    @Produces({MediaType.APPLICATION_XML}) // "text/xml"
    public byte[] getAcuseRets(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if (uuid != null && !uuid.isEmpty()) {
                    ERetenciones erets = edao.getRetsUuid(uuid);
                    if ( erets != null )  {
                        MongoDAO.MongoDocument mdoc = mdao.getAcuseFromMongo(erets.getMongoCollection(), uuid);
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
                    ECfdi cfdi = edao.getCfdiUuid(uuid);
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
    @Path("pdfRets") // Regresa el PDF usando el XML de impresión
    @Produces({"application/pdf"})
    public byte[] getPdfRets(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if (uuid != null && !uuid.isEmpty()) {
                    ERetenciones erets = edao.getRetsUuid(uuid);
                    if ( erets != null )  {
                        byte[] bytes = getXmlPRets(uuid, httpRequest);
                        if (bytes  != null && bytes.length > 1024) {
                            bytes = new ReportUtils().getPdfRets(
                                bytes,
                                ldao.getPlantillaById(erets.getItems()),
                                erets.getEstadoDocumento() == 0, // Cancelado
                                erets.getModo() == 0, // Vista Previa si se generó en modo Desarrollo
                                (byte[])mdao.getLogoMongo("DatosPersonales", erets.getClientes().getRfc())
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
    
    @GET
    @Path("zipRets") // Regresa un zip con el XML y PDF del UUID solicitado
    @Produces({MediaType.APPLICATION_OCTET_STREAM}) // "application/octet-stream"
    public byte[] getZipRets(@PathParam("uuid") String uuid, @Context HttpServletRequest httpRequest)
    {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                byte[] pdf = getPdfRets(uuid, httpRequest);
                byte[] xml = getXmlRets(uuid, httpRequest);

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
