
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
import model.ImageGallery;


public class ImageGalleryDAO {
    
    /**
     * getImageByGallery.<br>
     * 
     * @param id
     * @return all image of a gallery .
     */
    public List<ImageGallery> getImageByGallery(int id) throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        OldDBContext db = new OldDBContext();
        String sql = "select * from ImageGallery where gallery_id = ?";
        List<ImageGallery> list = new ArrayList<>();
        
        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ImageGallery ig = new ImageGallery(rs.getString("image"), rs.getInt("gallery_id"));
                
                list.add(ig);
            }
            
        } catch (Exception ex) {
            throw ex;
        }finally{
            db.closeConnection(rs, pstmt, conn);
        }
        
        return list;
    }
    public List<ImageGallery> pagging(int pageIndex, int pageSize, int galId) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OldDBContext db = new OldDBContext();
        ArrayList<ImageGallery> list = new ArrayList<>();
        //get value between start to end
        int start = (pageIndex - 1) * pageSize + 1;
        int end = pageIndex * pageSize;
        String sql = "select * from (select ROW_NUMBER() over (order by gallery_id ASC) as No,\n" +
"                * from ImageGallery where gallery_id = ?) as x where No between ? and ?";
        try {
            connection = new OldDBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setObject(1, galId);
            ps.setObject(2, start);
            ps.setObject(3, end);
            rs = ps.executeQuery();
            while (rs.next()) {
                ImageGallery g = new ImageGallery(rs.getString("image"),
                                             rs.getInt("gallery_id"));
                list.add(g);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            db.closeConnection(rs, ps, connection);
        }
    }
    
    public int numberOfResult(int galId) throws Exception {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        OldDBContext db = new OldDBContext();
        String sql = "Select count(gallery_id) from ImageGallery where gallery_id = ?";
        int count = 0;
        try {
            connection = new OldDBContext().getConnection();
            ps = connection.prepareStatement(sql);
            ps.setObject(1, galId);
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
}
