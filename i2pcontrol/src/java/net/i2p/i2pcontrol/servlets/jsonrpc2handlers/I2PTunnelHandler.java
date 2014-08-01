package net.i2p.i2pcontrol.servlets.jsonrpc2handlers;

import com.thetransactioncompany.jsonrpc2.JSONRPC2Error;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Request;
import com.thetransactioncompany.jsonrpc2.JSONRPC2Response;
import com.thetransactioncompany.jsonrpc2.server.MessageContext;
import com.thetransactioncompany.jsonrpc2.server.RequestHandler;
import net.i2p.I2PAppContext;
import net.i2p.data.Hash;
import net.i2p.i2pcontrol.router.RouterManager;
import net.i2p.i2ptunnel.TunnelController;
import net.i2p.i2ptunnel.TunnelControllerGroup;
import net.i2p.router.RouterContext;
import net.i2p.util.Log;

import java.util.HashMap;

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
    private static TunnelControllerGroup _i2ptunnel;
    private static final Log _log = I2PAppContext.getGlobalContext().logManager().getLog(I2PTunnelHandler.class);


    static {
        try {
            _context = RouterManager.getRouterContext();
        } catch (Exception thrown) {
            _log.error("Unable to initialize Router Context.", thrown);
        }
        try {
            _i2ptunnel = TunnelControllerGroup.getInstance();
        }catch (Exception thrown) {
            _log.error("Unable to initialize I2PTunnel.", thrown);
        }
    }

    @Override
    public String[] handledRequests() {
        return new String[]{
                "tunnel_exists",
                "tunnel_new",
                "tunnel_del",
                "tunnel_start",
                "tunnel_stop",
                "tunnel_status",
                "tunnel_list",
                "tunnel_errors"
        };
    }

    TunnelController _getTunnelByName(String tunnelName) {
        for (TunnelController tun : _i2ptunnel.getControllers()) {
            if (tun.equals(tun)) { return tun; }
        }
        return null;
    }

    JSONRPC2Response _tunnel_exists(HashMap params) {
        Object tunnel_name = params.get("name");

        boolean exists = tunnel_name instanceof String && _getTunnelByName(tunnel_name.toString()) != null;

        HashMap result = new HashMap();
        result.put("exists", exists);
        result.put("name", tunnel_name);

        return new JSONRPC2Response(result, 0);
    }

    JSONRPC2Response _tunnel_new(HashMap params) {

        return null;
    }
    JSONRPC2Response _tunnel_del(HashMap params) {
        return null;
    }
    JSONRPC2Response _tunnel_start(HashMap params) {
        return null;
    }
    JSONRPC2Response _tunnel_stop(HashMap params) {
        return null;
    }
    JSONRPC2Response _tunnel_status(HashMap params) {
        return null;
    }
    JSONRPC2Response _tunnel_list(HashMap params) {
        HashMap result = new HashMap();
        for (TunnelController tun : _i2ptunnel.getControllers()) {
            result.put(tun.getName(), tun.getJSONStatus());
        }
        return new JSONRPC2Response(result, 0);
    }
    JSONRPC2Response _tunnel_errors(HashMap params) {
        return null;
    }

    private JSONRPC2Response internal_error(JSONRPC2Request req, String msg) {
        return new JSONRPC2Response(
            new JSONRPC2Error(JSONRPC2Error.INTERNAL_ERROR.getCode(), msg),
            req.getID()
        );
    }

    private JSONRPC2Response method_not_found(String method) {
        return new JSONRPC2Response(
          new JSONRPC2Error(JSONRPC2Error.METHOD_NOT_FOUND.getCode(),
            method+" is not a callable method"), 0
        );
    }


    @Override
    public JSONRPC2Response process(JSONRPC2Request req, MessageContext requestCtx) {
        JSONRPC2Error err = JSONRPC2Helper.validateParams(null, req);

        if (err != null) { return new JSONRPC2Response(err, req.getID()); }
        if (_context == null) { return internal_error(req, "RouterContext not initialized. Query Failed."); }
        if (_i2ptunnel == null) { return internal_error(req, "I2PTunnel not initialize. Quert Failed."); }

        String method_name = req.getMethod();
        HashMap params = (HashMap) req.getParams();
        JSONRPC2Response resp = null;
        switch(method_name) {
            case "tunnel_exists":
                resp = _tunnel_exists(params);
                break;
            case "tunnel_new":
                resp = _tunnel_new(params);
                break;
            case "tunnel_del":
                resp = _tunnel_del(params);
                break;
            case "tunnel_start":
                resp = _tunnel_start(params);
                break;
            case "tunnel_stop":
                resp = _tunnel_stop(params);
                break;
            case "tunnel_status":
                resp = _tunnel_status(params);
                break;
            case "tunnel_errors":
                resp = _tunnel_errors(params);
            default:
                resp = method_not_found(method_name);
        }
        resp.setID(req.getID());
        return resp;
    }

}
