/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import function.CheckedFunction;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import mapper.RowMapper;

public class DBContext<T> {

    private final String dbName;
    private final String user;
    private final String password;
    private final String serverName;
    private final String portNumber;
    private final String image;

    public DBContext() throws NamingException {
//        Context ct = (Context) new InitialContext().lookup("java:comp/env");
//        serverName = ct.lookup("serverName").toString();
//        dbName = ct.lookup("dbName").toString();
//        portNumber = ct.lookup("1433").toString();
//        user = ct.lookup("user").toString();
//        password = ct.lookup("pass").toString();
//        image = ct.lookup("image").toString();
        dbName = "Photographer";
        user = "sa";
        password = "123456";
        serverName = "localhost";
        portNumber = "1433";
        image = "images/";
    }

    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, user, password);
    }

    public void closeConnection(Connection conn, PreparedStatement ps, ResultSet rs) throws SQLException {
        if (rs != null && !rs.isClosed()) {
            rs.close();
        }
        if (ps != null && !ps.isClosed()) {
            ps.close();
        }
        if (conn != null && !conn.isClosed()) {
            conn.close();
        }
    }

    public String getImagePath() throws Exception {
        return image;
    }

    public void setParameter(PreparedStatement st, Object... objects) throws SQLException {
        for (int i = 0; i < objects.length; i++) {
            Object ob = objects[i];
            int index = i + 1;
            if (ob instanceof Integer) {
                st.setInt(index, (Integer) ob);
            } else if (ob instanceof String) {
                st.setString(index, (String) ob);
            } else if (ob instanceof Date) {
                st.setDate(index, (Date) ob);
            } else if (ob instanceof Boolean) {
                st.setBoolean(index, (Boolean) ob);
            }
//            st.setObject(index, ob);
        }
    }
    
    public <T> T get(String sql, RowMapper<T> rowMapper, Object... objects) throws Exception {
        CheckedFunction<ResultSet, T> function = rs -> rs.next() ? rowMapper.mapRow(rs) : null;
        return query(sql, function, objects);
    }
    
    public <T> List<T> list(String sql, RowMapper<T> rowMapper, Object... objects) throws Exception {
        CheckedFunction<ResultSet, List<T>> function = rs -> {
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                list.add(rowMapper.mapRow(rs));
            }
            return list;
        };
        return query(sql, function, objects);
    }
    
    public Integer count(String sql, Object... parameters) throws Exception {
        CheckedFunction<ResultSet, Integer> function = rs -> rs.next() ? rs.getInt(1) : null;
        return query(sql, function, parameters);
    }
    
    private <R> R query(String sql, CheckedFunction<ResultSet, R> function, Object... objects) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            setParameter(statement, objects);
            resultSet = statement.executeQuery();
            return function.apply(resultSet);
        } catch (Exception e) {
            throw e;
        } finally {
            closeConnection(conn, statement, resultSet);
        }
    }

//    public <T> T get(String sql, RowMapper<T> rowMapper, Object... objects) throws Exception {
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            conn = getConnection();
//            statement = conn.prepareStatement(sql);
//            setParameter(statement, objects);
//            resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return rowMapper.mapRow(resultSet);
//            }
//            return null;
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            closeConnection(conn, statement, resultSet);
//        }
//    }
//
//    public <T> List<T> list(String sql, RowMapper<T> rowMapper, Object... objects) throws Exception {
//        List<T> list = new ArrayList<>();
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            conn = getConnection();
//            statement = conn.prepareStatement(sql);
//            setParameter(statement, objects);
//            resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                list.add(rowMapper.mapRow(resultSet));
//            }
//            return list;
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            closeConnection(conn, statement, resultSet);
//        }
//    }
//
//    public int count(String sql, Object... parameters) throws Exception {
//        Connection connection = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        int count = 0;
//        try {
//            connection = getConnection();
//            statement = connection.prepareStatement(sql);
//            setParameter(statement, parameters);
//            resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                count = resultSet.getInt(1);
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            closeConnection(connection, statement, resultSet);
//        }
//        return count;
//    }

//    public Integer insert(String sql, Object... objects) throws Exception {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        ResultSet rs = null;
//        try {
//            Integer id = null;
//            conn = getConnection();
//            conn.setAutoCommit(false);
//            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//            setParameter(ps, objects);
//            ps.executeUpdate();
//            rs = ps.getGeneratedKeys();
//            while (rs.next()) {
//                id = rs.getInt(1);
//            }
//            conn.commit();
//            return id;
//        } catch (SQLException e) {
//            if (conn != null) {
//                try {
//                    conn.rollback();
//                } catch (SQLException e2) {
//                    throw e2;
//                }
//            }
//            throw e;
//        } finally {
//            closeConnection(conn, ps, null);
//        }
//    }

//    public void update(String sql, Object... parameters) throws Exception {
//        Connection conn = null;
//        PreparedStatement ps = null;
//        try {
//            conn = getConnection();
//            conn.setAutoCommit(false);
//            ps = conn.prepareStatement(sql);
//            setParameter(ps, parameters);
//            ps.executeUpdate();
//            conn.commit();
//        } catch (SQLException e) {
//            conn.rollback();
//            throw e;
//        } finally {
//            closeConnection(conn, ps, null);
//        }
//    }

//    public boolean checkExist(String sql, RowMapper rowMapperm, Object... objects) throws Exception {
//        Connection conn = null;
//        PreparedStatement statement = null;
//        ResultSet resultSet = null;
//        try {
//            conn = getConnection();
//            statement = conn.prepareStatement(sql);
//            setParameter(statement, objects);
//            resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                return true;
//            }
//        } catch (Exception e) {
//
//            throw e;
//        } finally {
//            closeConnection(conn, statement, resultSet);
//        }
//        return false;
//    }

}
