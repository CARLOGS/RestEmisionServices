package mx.com.itsb.ws.rest; //Esta es la estructura de paquete creada

import javax.ws.rs.*; //Importamos la librer�a para manejar RESTful
import javax.ws.rs.core.Response;

@Path("getMessage2/{type}") //Especificamos una ruta que se debe usar para invocar este m�todo y un par�metro (tipo)
public class WebServiceTest {

    @GET  //Indicamos que este m�todo se ejecutar� al recibir una petici�n por get
    @Produces({"text/plain", "text/html", "text/xml", "application/json"}) //Indicamos que el tipo de salida es texto plano, XML, HTML o JSON
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String mostrarMensaje(@PathParam("type") String tipo, @QueryParam("name") String name)//M�todo que recibe como parametro el valor de type en la URL
    {
        if (tipo.equalsIgnoreCase("texto")) {
            return "�ste es mi primer servicio RESTful con Java usando Texto " + name;
        } else if (tipo.equalsIgnoreCase("html")) {
            return "<html lang='es'><head><meta charset='UTF-8'/><title>WS</title></head><body><h1>�ste es mi primer servicio RESTful con Java en HTML " + name + "</h1></body></html>";
        } else if (tipo.equalsIgnoreCase("xml")) {
            return "<?xml version='1.0' encoding='UTF-8'?><root><value>�ste es mi primer servicio RESTful con Java wn XML " + name + "</value></root>";
        } else if (tipo.equalsIgnoreCase("json")) {
            return "{\"root\":{\"value\":\"�ste es mi primer servicio RESTful con Java en JSon " + name + "\"}}";
        } else { 
            return "Tipo no soportado " + name;
        }
    }
    
    @Path("/getAddress")
    public AddressResource getAddressResource() {
        return new AddressResource();
    }
    
    public class AddressResource {
        @GET
        public Response getUserName() {
            return Response
                .status(200)
                .entity("address")
                .build();
        }
    }
}
