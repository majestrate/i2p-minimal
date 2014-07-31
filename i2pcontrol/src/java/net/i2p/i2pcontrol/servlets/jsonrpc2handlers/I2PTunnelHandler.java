package net.i2p.i2pcontrol.servlets.jsonrpc2handlers;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;
import net.i2p.I2PAppContext;
import net.i2p.i2pcontrol.router.RouterManager;
import net.i2p.router.RouterContext;
import net.i2p.util.Log;

/**
 * Created by jeff on 7/26/14.
 *
 *
 * handled functions:
 *
 * tunnel_new - create new tunnel
 *
 * tunnel_del - delete stopped tunnel
 *
 * tunnel_start - start an existing tunnel
 *
 * tunnel_stop - stop an existing tunnel
 *
 * tunnel_status - get the status of an existing tunnel
 *
 */
public class I2PTunnelHandler implements RequestHandler {

    private static RouterContext _context;
    private static final Log _log = I2PAppContext.getGlobalContext().logManager().getLog(I2PTunnelHandler.class);


    static {
        try {
            _context = RouterManager.getRouterContext();
        } catch (Exception thrown) {
            _log.error("Unable to initialize Router Context.", thrown);
        }
    }

    @Override
    public String[] handledRequests() {
        return new String[]{
                "tunnel_new",
                "tunnel_del",
                "tunnel_start",
                "tunnel_stop",
                "tunnel_status"
        };
    }

    JSONRPC2Response _tunnel_new(JSONRPC2Request req) {
        return null;
    }
    JSONRPC2Response _tunnel_del(JSONRPC2Request req) {
        return null;
    }
    JSONRPC2Response _tunnel_start(JSONRPC2Request req) {
        return null;
    }
    JSONRPC2Response _tunnel_stop(JSONRPC2Request req) {
        return null;
    }
    JSONRPC2Response _tunnel_status(JSONRPC2Request req) {
        return null;
    }
    @Override
    public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
        String method_name = request.getMethod();
        switch(method_name) {
            case "tunnel_new":
                return _tunnel_new(request);
            case "tunnel_del":
                return _tunnel_del(request);
            case "tunnel_start":
                return _tunnel_start(request);
            case "tunnel_stop":
                return _tunnel_stop(request);
            case "tunnel_status":
                return _tunnel_status(request);
        }
        return null;
    }

}
