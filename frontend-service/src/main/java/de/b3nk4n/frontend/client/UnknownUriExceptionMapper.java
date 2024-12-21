package de.b3nk4n.frontend.client;

import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import java.util.Locale;
import java.util.logging.Logger;

@Provider
public class UnknownUriExceptionMapper implements ResponseExceptionMapper<UnknownUriException> {

    private static final Logger LOG = Logger.getLogger(UnknownUriExceptionMapper.class.getName());

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        LOG.info(() -> String.format(Locale.ROOT, "status = %s", status));
        return status == 404;
    }

    @Override
    public UnknownUriException toThrowable(Response response) {
        return new UnknownUriException();
    }
}
