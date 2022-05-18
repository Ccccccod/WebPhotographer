
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
import model.Introduction;
import model.Share;


public class ShareDAO {
    
    /**
     * getShare.<br>
     * 
     * @return the share.
     */
    public List<Share> getShare() throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        OldDBContext db = new OldDBContext();
        String sql = "select * from Share";
        List<Share> list = new ArrayList<>();
        
        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Share sh = new Share(rs.getString("icon"),
                        rs.getString("social_network"), rs.getString("url"));
                
                list.add(sh);
            }
            
        } catch (Exception ex) {
            throw ex;
        }finally{
            db.closeConnection(rs, pstmt, conn);
        }
        
        return list;
    }
}
