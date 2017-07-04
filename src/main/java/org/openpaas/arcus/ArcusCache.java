package org.openpaas.arcus;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.ConnectionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Future;

public class ArcusCache {
    private final Logger logger = LoggerFactory.getLogger(ArcusCache.class);
    private ArcusClient client = null;

    private String serverAddress = null;
    private String serviceCode = null;

    public ArcusCache() {

    }

    public void setup() {
        System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.Log4JLogger");
        ConnectionFactoryBuilder cfb = new ConnectionFactoryBuilder();
        client = ArcusClient.createArcusClient(serverAddress, serviceCode, cfb);
    }

    public Future<Object> get(final String key) {
        if (logger.isDebugEnabled()) {
            logger.debug("[arcus] cache get : {}", key);
        }
        return client.asyncGet(key);
    }

    public <T> Future<Boolean> set(final String key, final int expireTime, final T value) {
        if (logger.isDebugEnabled()) {
            logger.debug("[arcus] cache set : {}", key);
        }
        return client.set(key, expireTime, value);
    }

    public void setServerAddress(String serviceAddress) {
        this.serverAddress = serviceAddress;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getServiceCode() {
        return serviceCode;
    }
}
