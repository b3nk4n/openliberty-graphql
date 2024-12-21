package de.b3nk4n.frontend.client;

import de.b3nk4n.models.JavaInfo;
import de.b3nk4n.models.SystemLoadData;
import de.b3nk4n.models.SystemMetrics;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;

@RegisterProvider(UnknownUriExceptionMapper.class)
public interface SystemClient extends AutoCloseable {

    @GET
    @Path("/properties/{property}")
    @Produces(MediaType.TEXT_PLAIN)
    String getProperty(@PathParam("property") String property)
            throws UnknownUriException, ProcessingException;

    @GET
    @Path("/properties/java")
    @Produces(MediaType.APPLICATION_JSON)
    JavaInfo getJava()
            throws UnknownUriException, ProcessingException;

    @PUT
    @Path("/note")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    Response putNote(String text)
            throws UnknownUriException, ProcessingException;

    @GET
    @Path("/metrics")
    @Produces(MediaType.APPLICATION_JSON)
    SystemMetrics getSystemMetrics()
            throws UnknownUriException, ProcessingException;

    @GET
    @Path("/metrics/load")
    @Produces(MediaType.APPLICATION_JSON)
    SystemLoadData getSystemLoad()
            throws UnknownUriException, ProcessingException;

}
