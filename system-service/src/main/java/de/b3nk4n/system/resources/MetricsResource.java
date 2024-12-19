package de.b3nk4n.system.resources;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import models.SystemLoadData;
import models.SystemMetrics;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.OperatingSystemMXBean;

@ApplicationScoped
@Path("metrics")
public class MetricsResource {
    private static final OperatingSystemMXBean OS_BEAN = ManagementFactory.getOperatingSystemMXBean();
    private static final MemoryMXBean MEM_BEAN = ManagementFactory.getMemoryMXBean();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public SystemMetrics getSystemMetrics() {
        return new SystemMetrics(
                OS_BEAN.getAvailableProcessors(),
                MEM_BEAN.getHeapMemoryUsage().getMax(),
                MEM_BEAN.getNonHeapMemoryUsage().getMax()
        );
    }

    @GET
    @Path("/load")
    @Produces(MediaType.APPLICATION_JSON)
    public SystemLoadData getSystemLoad() {
        return new SystemLoadData(
                OS_BEAN.getSystemLoadAverage(),
                MEM_BEAN.getHeapMemoryUsage().getUsed(),
                MEM_BEAN.getNonHeapMemoryUsage().getUsed()
        );
    }
}
