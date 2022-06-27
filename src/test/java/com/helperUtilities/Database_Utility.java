package com.helperUtilities;

import com.dataproviderUtilities.ConfigFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class Database_Utility {

    public ConfigFileReader configFileReader;
    Logger log = LogManager.getLogger(Database_Utility.class);

    public Database_Utility() {
        configFileReader = new ConfigFileReader();
    }


    /**
     * used to create JDBC connection using JDBC URL, username and password
     */
    public Connection createJDBCConnection(String DB_DriverName) {
        String DB_URL = null, DB_username = null, DB_password = null;
        Connection conn = null;
        String driver = "";
        if (DB_DriverName.equalsIgnoreCase("mariadb") || DB_DriverName.equalsIgnoreCase("mysql")) {
            driver = "org.mariadb.jdbc.Driver";
            DB_URL = configFileReader.getProperties().getProperty("MARIADB_URL");
            DB_username = configFileReader.getProperties().getProperty("MARIADB_USERNAME");
            DB_password = configFileReader.getProperties().getProperty("MARIADB_PASSWORD");
        } else if (DB_DriverName.equalsIgnoreCase("POSTGRE")) {
            driver = "org.postgresql.Driver";
            DB_URL = configFileReader.getProperties().getProperty("RDBMS_POSTGRE_URL");
            DB_username = configFileReader.getProperties().getProperty("POSTGRE_USERNAME");
            DB_password = configFileReader.getProperties().getProperty("POSTGRE_PASSWORD");
        } else if (DB_DriverName.equalsIgnoreCase("DB2")) {
            driver = "com.ibm.db2.jcc.DB2Driver";
            DB_URL = configFileReader.getProperties().getProperty("DB2_URL");
            DB_username = configFileReader.getProperties().getProperty("RDBMS_DB2_USERNAME");
            DB_password = configFileReader.getProperties().getProperty("RDBMS_DB2_PASSWORD");
        } else if (DB_DriverName.equalsIgnoreCase("ORACLE")) {
            driver = "oracle.jdbc.driver.OracleDriver";
            DB_URL = configFileReader.getProperties().getProperty("ORACLE_URL");
            DB_username = configFileReader.getProperties().getProperty("ORACLE_USERNAME");
            DB_password = configFileReader.getProperties().getProperty("ORACLE_PASSWORD");
        }
        try {
            Class.forName(driver);
            assert DB_URL != null;
            conn = DriverManager.getConnection(DB_URL, DB_username, DB_password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info(DB_DriverName + " Database connection Created successfully");
        return conn;

    }

    /**
     * used to execute SQL with given connection instance
     */
    public ResultSet executeSQL(String sql, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet res = stmt.executeQuery(sql);
        if (!res.next()) {
            return null;
        } else {
            return res;
        }
    }
    public boolean execute(String sql, Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        return stmt.execute(sql);

    }
}
