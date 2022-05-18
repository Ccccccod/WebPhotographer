/*
 * OldDBContext.java
 * 
 * All Rights Reserved 
 * Copyright (c) 2019 FPT University
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;

public class OldDBContext {

    static InitialContext initial;
    static Context context;
    static String Dbname, dbusername, dbpass, host, portnumber, img;

    static {
        try {
//            initial = new InitialContext();
//            Object obj = initial.lookup("java:comp/env");
//            context = (Context) obj;
//            Dbname = context.lookup("dbName").toString();
//            dbusername = context.lookup("user").toString();
//            dbpass = context.lookup("pass").toString();
//            host = context.lookup("serverName").toString();
//            portnumber = context.lookup("portNumber").toString();
//            img = context.lookup("image").toString();
            Dbname = "Photographer";
            dbusername = "sa";
            dbpass = "123456";
            host = "localhost";
            portnumber = "1433";
            img = "images/";
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + host + ":" + portnumber + ";databaseName=" + Dbname, dbusername, dbpass);
//        Connection conn = DriverManager.getConnection("jdbc:sqlserver://" + host + ":" + portnumber + ";databaseName=" + Dbname);
        return conn;
    }

    public void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }

    public String getImagePath() throws Exception {
        return img;
    }
}
