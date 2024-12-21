package de.b3nk4n.frontend;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("ping")
public class PingResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String ping() {
        return "pong";
    }
}
