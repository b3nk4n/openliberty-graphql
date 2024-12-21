package de.b3nk4n.frontend.client;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import java.net.URI;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class SystemClientFactory {
    private static final Map<String, SystemClient> CLIENTS = new ConcurrentHashMap<>();

    @Inject
    @ConfigProperty(name = "system.http.port", defaultValue = "9080")
    private int systemPort;

    public SystemClient getClient(String hostname) {
        SystemClient client = CLIENTS.get(hostname);

        if (client == null) {
            String uriString = String.format(Locale.ROOT, "http://%s:%d/api/system", hostname, systemPort);
            URI baseUri = URI.create(uriString);
            client = RestClientBuilder
                    .newBuilder()
                    .baseUri(baseUri)
                    .register(UnknownUriExceptionMapper.class)
                    .build(SystemClient.class);
            CLIENTS.put(hostname, client);
        }

        return client;
    }
}
