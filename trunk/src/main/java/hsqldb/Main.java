package hsqldb;

// ==============================================================================
//Created on 2005-5-15
//$Id$
//==============================================================================

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main implements Constants{

    private static String getDbName() {
        return System.getProperty("db", DEFAULT_DB_NAME);
    }

    public static void main(String[] args) {
        String dataDir = System.getProperty("data");
        if (dataDir == null) {
            dataDir = DEFAULT_DATA_DIR;
        }

        HsqldbRunner runner = new HsqldbRunner();
        runner.addDatabase(dataDir + "/" + getDbName());
        new Thread(runner).start();
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                shutdownDb();
            }
        }));
    }

    private static void shutdownDb() {
        System.out.println("Shutdown db");
        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (Exception e) {
            System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
            e.printStackTrace();
            return;
        }
        Connection c = null;
        Statement stmt = null;
        try {
            c = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/" + getDbName(), "sa", "");
            stmt = c.createStatement();
            stmt.execute("SHUTDOWN");
        } catch (SQLException e) {
            System.err.println("ERROR: failed to shutdown DB.");
            e.printStackTrace();
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
