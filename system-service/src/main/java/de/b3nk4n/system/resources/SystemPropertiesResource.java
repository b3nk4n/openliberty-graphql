package de.b3nk4n.system.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import de.b3nk4n.models.JavaInfo;

@ApplicationScoped
@Path("properties")
public class SystemPropertiesResource {

    @GET
    @Path("{property}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getProperty(@PathParam("property") String property) {
        return System.getProperty(property);
    }

    @GET
    @Path("java")
    @Produces(MediaType.APPLICATION_JSON)
    public JavaInfo getJava() {
        return new JavaInfo(
                System.getProperty("java.version"),
                System.getProperty("java.vendor")
        );
    }

    @PUT
    @Path("note")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public Response putNote(String text) {
        System.setProperty("note", text);
        return Response.ok().build();
    }

}
