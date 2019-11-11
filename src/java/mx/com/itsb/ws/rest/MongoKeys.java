package mx.com.itsb.ws.rest;

import fe.db.mongo.ItKeys;
import fe.db.mongo.MongoDAO;
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
 * REst de recuperación y almacenamiento de llaves (CSD / FIEL)
 * 
 * @author CarloGS
 */
@Path("keys")
public class MongoKeys {
    private MongoDAO mdao;

    @GET
    @Path("fiel/{rfc}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getFiel(@PathParam("rfc") String rfc, @Context HttpServletRequest httpRequest) {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if ( rfc != null && !rfc.isEmpty())  {
                    
                    // Recupera llaves
                    ItKeys keys = mdao.getKeys("fiel", rfc);
                    
                    if (keys != null)
                        return Response.ok().entity(keys).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("fiel/{rfc}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response setFiel(@PathParam("rfc") String rfc, ItKeys keys, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if ( rfc != null && !rfc.isEmpty())  {
                    
                    // Almacena llaves
                    mdao.setKeys(keys, "fiel", rfc);
                    
                    return Response.ok().entity("Ok").build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }

    @GET
    @Path("csd/{rfc}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getCSD(@PathParam("rfc") String rfc, @Context HttpServletRequest httpRequest) {
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if ( rfc != null && !rfc.isEmpty())  {
                    
                    // Recupera llaves
                    ItKeys keys = mdao.getKeys("fiel", rfc);
                    
                    if (keys != null)
                        return Response.ok().entity(keys).build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("csd/{rfc}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.TEXT_PLAIN})
    public Response setCSD(@PathParam("rfc") String rfc, ItKeys keys, @Context HttpServletRequest httpRequest) throws Exception {
        String error = "";
        if ( "127.0.0.1".equals(httpRequest.getRemoteAddr()) )
            try {
                if ( mdao == null )
                        mdao = new MongoDAO();
                if ( rfc != null && !rfc.isEmpty())  {
                    
                    // Almacena llaves
                    mdao.setKeys(keys, "fiel", rfc);
                    
                    return Response.ok().entity("Ok").build();
                }
            } catch (Exception e) {
                e.printStackTrace(System.out);
                error = e.getClass().getName() + " - " + e.getMessage();
            }
        
        return Response.status(Response.Status.NOT_ACCEPTABLE).header("error", error).build();
    }
}
