package net.i2p.i2pcontrol.servlets.jsonrpc2handlers;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;
import net.i2p.i2pcontrol.router.RouterManager;
import net.i2p.util.Log;

/**
 * Created by jeff on 7/26/14.
 */
public class I2PTunnelHandler implements RequestHandler {


    private static final Log _log = RouterManager.getRouterContext().logManager().getLog(I2PTunnelHandler.class);

    @Override
    public String[] handledRequests() {
        return new String[] {"i2ptunnel"};
    }

    @Override
    public JSONRPC2Response process(JSONRPC2Request request, MessageContext requestCtx) {
        if (request.getMethod().equals("i2ptunnel")) {
            return process(request);
        } else {
            return new JSONRPC2Response(JSONRPC2Error.METHOD_NOT_FOUND, request.getID());
        }
    }

    private JSONRPC2Response process(JSONRPC2Request request) {
        JSONRPC2Error err = JSONRPC2Helper.validateParams(null, request);
        if ( err != null ) {
            return new JSONRPC2Response(err, request.getID());
        }

    }
}
