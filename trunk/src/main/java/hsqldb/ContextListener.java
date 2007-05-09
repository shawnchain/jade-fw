//==============================================================================
// Created on 2005-5-16
// $Id$
//==============================================================================
package hsqldb;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

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

public class ContextListener implements ServletContextListener, Constants {
    private boolean dbStarted = false;

    private static final Logger logger = Logger.getLogger(ContextListener.class);
    
    private String dataDir = null;
    private String dbName = null;
    
    public void contextInitialized(ServletContextEvent event) {
        String appHome = System.getProperty("app.home");
        if (appHome == null)
            appHome = event.getServletContext().getInitParameter("app.home");
        if (appHome == null) {
            // Try to use the "WEB-INF" as default location
            appHome = event.getServletContext().getRealPath("/WEB-INF");
        }

        dataDir =  event.getServletContext().getInitParameter("data.dir");
        if(dataDir != null){
            dataDir = appHome + dataDir;
        }else{
            dataDir = appHome + getDataDir(); // default is: "/data"
        }
        
        dbName = event.getServletContext().getInitParameter("db.name");
        if(dbName == null){
            dbName = getDbName(); // default is: "db"
        }

        if (isDataReady(dataDir)) {
            HsqldbRunner task = new HsqldbRunner();
            task.addDatabase(dataDir + "/" + dbName);
            logger.info("[Startup HSQLDB " + dbName + "@" + dataDir + "]");
            Thread t = new Thread(task);
            t.start();
            dbStarted = true;
        }
    }

    private static String getDataDir() {
        return System.getProperty("data", DEFAULT_DATA_DIR);
    }

    private static String getDbName() {
        return System.getProperty("db", DEFAULT_DB_NAME);
    }

    private boolean isDataReady(String dataDirPath) {
        File _file = new File(dataDirPath);
        File _flag = new File(_file, DB_CONFIGURED_FLAG_FILE);
        if (_flag.exists()) {
            return true;
        }

        if (!_file.exists()) {
            if (!_file.mkdirs()) {
                logger.warn("Could not create data location \"" + dataDirPath
                        + "\" mkdirs() failed. DB auto startup sequence aborted, application may not work properly");
                return false;
            }
        }

        if (!_file.canWrite()) {
            logger
                    .warn("The HSQLDB data location \""
                            + dataDirPath
                            + "\" exists but seems not writable. Please check the permission. DB auto startup sequence aborted, application may not work properly");
            return false;
        }

        // Indicate hibernate to create the schema
        System.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        _flag.mkdirs();
        /*
         * if(_file.isFile()){ logger.warn("The HSQLDB data location \"" + dataDirPath + "\" seems to be a file, startup
         * aborted, application may not work properly"); return false; }
         * 
         * if(_file.exists()){ // check whether the mycrm files exists File[] _files = _file.listFiles(new FileFilter(){
         * public boolean accept(File pathname) { return pathname.isFile() &&
         * pathname.getName().startsWith(DEFAULT_DB_NAME); } }); }else{ } if(!_file.exists() || _file.isFile()){
         * logger.warn("Invalid HSQLDB data path: " + dataDirPath + ", startup aborted, application may not work
         * properly"); return false; }
         */
        return true;
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        if (!dbStarted) {
            return;
        }
        logger.info("[Shutdown HSQLDB...]");
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception e) {
            System.err.println("ERROR: can't load HSQLDB JDBC driver class.");
            return;
        }
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/" + dbName, "sa", "");
            stmt = c.createStatement();
            stmt.execute("SHUTDOWN");
        } catch (SQLException e) {
            System.err.println("ERROR: failed to shutdown DB. " + e.getMessage());
            // e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e3) {
            }
            try {
                c.close();
            } catch (SQLException e2) {
            }
        }
    }

}
