package de.b3nk4n.frontend;

import de.b3nk4n.frontend.client.SystemClient;
import de.b3nk4n.frontend.client.SystemClientFactory;
import de.b3nk4n.frontend.client.UnknownUriException;
import de.b3nk4n.models.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.ProcessingException;
import org.eclipse.microprofile.graphql.*;

import java.util.ArrayList;
import java.util.List;

@GraphQLApi
public class GraphqlService {
    @Inject
    private SystemClientFactory systemClientFactory;

    @Query("system")
    @NonNull
    @Description("Gets information about the system")
    public SystemInfo getSystemInfo(@Name("hostname") String hostname)
            throws ProcessingException, UnknownUriException {
        SystemClient systemClient = systemClientFactory.getClient(hostname);
        return new SystemInfo(
                hostname,
                systemClient.getProperty("user.name"),
                systemClient.getProperty("os.name"),
                systemClient.getProperty("os.version"),
                systemClient.getProperty("note"),
                null // filled via resolver
        );
    }

    @Mutation("editNote")
    @Description("Changes the note set for the system")
    public boolean editNote(@Name("hostname") String hostname,
                            @Name("note") String note)
            throws ProcessingException, UnknownUriException {
        SystemClient systemClient = systemClientFactory.getClient(hostname);
        systemClient.putNote(note);
        return true;
    }

    @Query("systemLoad")
    @Description("Gets system load data from the systems")
    public SystemLoad[] getSystemLoad(@Name("hostnames") String[] hostnames)
            throws ProcessingException {
        if (hostnames == null || hostnames.length == 0) {
            return new SystemLoad[0];
        }

        List<SystemLoad> systemLoads = new ArrayList<>(hostnames.length);
        for (String hostname : hostnames) {
            systemLoads.add(new SystemLoad(
                    hostname,
                    null // filled via resolver
            ));
        }

        return systemLoads.toArray(new SystemLoad[0]);
    }

    @NonNull
    public SystemMetrics systemMetrics(@Source @Name("system") SystemInfo systemInfo)
            throws ProcessingException, UnknownUriException {
        String hostname = systemInfo.hostName();
        SystemClient systemClient = systemClientFactory.getClient(hostname);
        return systemClient.getSystemMetrics();
    }

    @NonNull
    public JavaInfo java(@Source @Name("system") SystemInfo systemInfo)
            throws ProcessingException, UnknownUriException {
        String hostname = systemInfo.hostName();
        SystemClient systemClient = systemClientFactory.getClient(hostname);
        return systemClient.getJava();
    }

    public SystemLoadData loadData(@Source @Name("systemLoad") SystemLoad systemLoad)
            throws ProcessingException, UnknownUriException {
        String hostname = systemLoad.hostName();
        SystemClient systemClient = systemClientFactory.getClient(hostname);
        return systemClient.getSystemLoad();
    }
}
