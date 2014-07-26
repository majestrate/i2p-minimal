package net.i2p.client.streaming.impl;

import java.net.SocketTimeoutException;

import net.i2p.I2PException;
import net.i2p.client.streaming.AcceptingChannel;
import net.i2p.client.streaming.I2PServerSocket;
import net.i2p.client.streaming.I2PSocket;
import net.i2p.client.streaming.I2PSocketManager;

/**
 * Bridge to allow accepting new connections
 *
 */
class I2PServerSocketFull implements I2PServerSocket {
    private final I2PSocketManagerFull _socketManager;
    
    public I2PServerSocketFull(I2PSocketManagerFull mgr) {
        _socketManager = mgr;
    }
    
    /**
     * Warning, unlike regular ServerSocket, may return null
     * 
     * @return I2PSocket OR NULL
     * @throws net.i2p.I2PException
     * @throws SocketTimeoutException 
     */
    public I2PSocket accept() throws I2PException, SocketTimeoutException {
        return _socketManager.receiveSocket();
    }

    /**
     *  Unimplemented, unlikely to ever be implemented.
     *
     *  @deprecated
     *  @return null always
     *  @since 0.8.11
     */
    public synchronized AcceptingChannel getChannel() {
        return null;
    }
    
    public long getSoTimeout() {
        return _socketManager.getConnectionManager().getSoTimeout();
    }
    
    public void setSoTimeout(long x) {
        _socketManager.getConnectionManager().setSoTimeout(x);
    }
    /**
     * Close the connection.
     */
    public void close() {
        _socketManager.getConnectionManager().setAllowIncomingConnections(false);
    }

    /**
     * 
     * @return _socketManager
     */
    public I2PSocketManager getManager() {
        return _socketManager;
    }
}
