/**
 * Copyright (c) 2010-2015, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.edimaxsmartplug.internal;

/**
 * Information about a connect.
 *
 * @author Heinz
 * @author Michael Zapf
 *
 */
public class ConnectionInformation {

    private String username;
    private String password;
    private String host;
    private int port;

    public ConnectionInformation(String user, String pw, String target, int portnum) {
        username = user;
        password = pw;
        host = target;
        port = portnum;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

}
