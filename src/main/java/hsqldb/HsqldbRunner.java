//==============================================================================
// Created on 2005-5-10
// $Id$
//==============================================================================
package hsqldb;

import java.util.ArrayList;
import java.util.List;

import org.hsqldb.Server;

/**
 * <p>
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2003-2004 NonSoft.com
 * </p>
 * 
 * @author Shawn Chain
 * @version 1.0, $Revision$, $Date$
 */

public class HsqldbRunner implements Runnable {
    private String address = "localhost";

    private int port = 9001;

    private boolean silent = true;

    private boolean trace = true;

    private List databases = new ArrayList();

    public HsqldbRunner addDatabase(String path) {
        databases.add(path);
        return this;
    }

    /**
     * @return Returns the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     *            The address to set.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return Returns the databases.
     */
    public List getDatabases() {
        return databases;
    }

    /**
     * @param databases
     *            The databases to set.
     */
    public void setDatabases(List databases) {
        this.databases = databases;
    }

    /**
     * @return Returns the dbnames.
     */
    public String[] getDbnames() {
        return dbnames;
    }

    /**
     * @param dbnames
     *            The dbnames to set.
     */
    public void setDbnames(String[] dbnames) {
        this.dbnames = dbnames;
    }

    /**
     * @return Returns the port.
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port
     *            The port to set.
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return Returns the silenct.
     */
    public boolean isSilent() {
        return silent;
    }

    /**
     * @param silenct
     *            The silenct to set.
     */
    public void setSilent(boolean silent) {
        this.silent = silent;
    }

    /**
     * @return Returns the trace.
     */
    public boolean isTrace() {
        return trace;
    }

    /**
     * @param trace
     *            The trace to set.
     */
    public void setTrace(boolean trace) {
        this.trace = trace;
    }

    private String[] dbnames;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {

        String[] args = null;

        ArrayList list = new ArrayList();

        if (address != null) {
            list.add("-address");
            list.add(address);
        }

        if (port != 0) {
            list.add("-port");
            list.add("" + port);
        }

        list.add("-silent");
        list.add("" + silent);

        list.add("-trace");
        list.add("" + trace);

        for (int i = 0; i < databases.size(); i++) {
            list.add("-database." + i);
            list.add((String) databases.get(i));
            list.add("-dbname." + i);
            String database = (String) databases.get(i);
            String dbname = database.substring(database.lastIndexOf("/") + 1);
            list.add(dbname);
        }

        args = (String[]) list.toArray(new String[0]);

        Server.main(args);
    }

}
