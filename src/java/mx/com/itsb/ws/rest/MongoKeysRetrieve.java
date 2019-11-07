/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.itsb.ws.rest;

import fe.db.mongo.ItKeys;
import fe.db.mongo.MongoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REst de recuperación de llaves (CSD / FIEL)
 * 
 * @author CarloGS
 */
@Path("keys")
public class MongoKeysRetrieve {
    private MongoDAO mdao;
    
    @GET
    @Path("fiel/{rfc}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getXml(@PathParam("rfc") String rfc, @Context HttpServletRequest httpRequest) {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if ( rfc != null && !rfc.isEmpty())  {
                    ItKeys keys = mdao.getKeys("fiel", rfc);
                    if (keys != null)
                        return Response.ok().entity(keys).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
