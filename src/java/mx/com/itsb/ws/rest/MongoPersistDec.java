/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itsb.ws.rest;

import fe.db.mongo.dec.MongoJsonDao;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.WebServiceContext;
import org.json.JSONObject;

/**
 *
 * @author CarloGS
 */
@Path("mongoDec/{uuid}") //Especificamos una ruta que se debe usar para invocar este método y un parámetro (tipo)
public class MongoPersistDec {
    @Resource WebServiceContext wsContext;
    
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response saveMongo(@PathParam("uuid") String uuid, InputStream is, @Context HttpServletRequest httpRequest) throws Exception {
        System.out.println("IP: " + httpRequest.getRemoteAddr());
        
        JSONObject json = getJson(is);
        
        MongoJsonDao jdao = new MongoJsonDao();
        JSONObject jresp = jdao.saveXml(json);
        
        return Response.status(200).entity(jresp).build();
    }
    
    @DELETE
    @Path("{coleccion}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delMongo(@PathParam("uuid") String uuid, @PathParam("coleccion") String coleccion, @Context HttpServletRequest httpRequest) throws Exception {
        System.out.println("IP: " + httpRequest.getRemoteAddr());
        
        MongoJsonDao jdao = new MongoJsonDao();
        JSONObject jresp = jdao.delXml(uuid, coleccion);
        
        return Response.status(200).entity(jresp).build();
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("{collection}")
    public String getMongo(@PathParam("uuid") String uuid, @PathParam("collection") String collection, @Context HttpServletRequest httpRequest) throws Exception {
        System.out.println("IP: " + httpRequest.getRemoteAddr());
        
        MongoJsonDao mjdao = new MongoJsonDao();
        JSONObject jresp = mjdao.getXml(uuid, collection);
        
        return jresp.toString();
    }
    
    private JSONObject getJson(InputStream is) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int c = is.read(bytes);
        
        while (c != -1) {
            baos.write(bytes, 0 , c);
            c = is.read(bytes);
        }
        is.close();
        
        return new JSONObject(new String(baos.toByteArray()));
    }
}
