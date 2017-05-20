package com.micromata.webengineering.demo;

import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import java.net.InetAddress;

/**
 * This Service provides Server-Address information.
 *
 * Created by Jonas Scherbaum on 20.05.2017.
 */
@Service
@Configuration
public class ServerAddressService implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    private int port;

    /**
     * Return server URL with http:// prefix.
     *
     * @return server URL.
     */
    public String getServerURL() {
        return "http://" + getHostName() + ":" + getPort();
    }

    /**
     * Return the host address as an IP address.
     *
     * @return address
     */
    public String getHostAddress() {
        return InetAddress.getLoopbackAddress().getHostAddress();
    }

    /**
     * Return the port of the application.
     *
     * @return port
     */
    public int getPort() {
        return port;
    }

    /**
     * Return the host address as a DNS-resolvable name.
     *
     * @return address
     */
    public String getHostName() {
        return InetAddress.getLoopbackAddress().getHostName();
    }

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        port = event.getEmbeddedServletContainer().getPort();
    }
}
