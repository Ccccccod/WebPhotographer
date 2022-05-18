
package dao3;

import context.OldDBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Gallery;


public class GalleryDAO {

    /**
     * getTop3Gallery.<br>
     *
     * @return 3 galleries.
     */
    public List<Gallery> getTop3Gallery() throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        OldDBContext db = new OldDBContext();
        String sql = "Select top 3 * from Gallery";
        List<Gallery> list = new ArrayList<>();

        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Gallery ga = new Gallery(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("image"));

                list.add(ga);
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, pstmt, conn);
        }

        return list;
    }

    //Close connection, preparedStatement and resultSet
    public List<Gallery> pagging(int pageIndex, int pageSize) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OldDBContext db = new OldDBContext();
        ArrayList<Gallery> list = new ArrayList<>();
        //get value between start to end
        int start = (pageIndex - 1) * pageSize + 1;
        int end = pageIndex * pageSize;
        String sql = "select * from (select ROW_NUMBER() over (order by id ASC) as No,\n"
                + " * from Gallery) as x where No between ? and ?";
        try {
            connection = new OldDBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setObject(1, start);
            ps.setObject(2, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                Gallery g = new Gallery(rs.getInt("id"),
                                        rs.getString("name"),
                                        rs.getString("description"),
                                        rs.getString("image"));
                list.add(g);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
    }
    
    public int numberOfResult() throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OldDBContext db = new OldDBContext();
        String sql = "Select count(id) from Gallery";
        int count = 0;
        try {
            connection = new OldDBContext().getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
        return count;
    }

    /**
     * getGalleryByID.<br>
     *
     * @param id
     * @return get the gallery by id.
     */
    public Gallery getGalleryByID(int id) throws Exception {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        OldDBContext db = new OldDBContext();
        String sql = "select * from Gallery where id = ?";

        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Gallery ga = new Gallery(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("image"));

                return ga;
            }

        } catch (Exception ex) {
            throw ex;
        } finally {
            db.closeConnection(rs, pstmt, conn);
        }

        return null;
    }
}
