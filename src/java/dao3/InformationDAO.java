
package dao3;

import context.OldDBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Information;
import model.Share;


public class InformationDAO {
    
    /**
     * getInfor.<br>
     * 
     * @return the information.
     */
    public Information getInfor() throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        OldDBContext db = new OldDBContext();
        String sql = "select * from Information";
        
        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Information info = new Information(rs.getString("address"),
                        rs.getString("city"),
                        rs.getString("country"),
                        rs.getString("tel"),
                        rs.getString("email")
                        , rs.getString("image"));
                
                return info;
            }
            
        } catch (Exception ex) {
            throw ex;
        }finally{
            db.closeConnection(rs, pstmt, conn);
        }
        
        return null;
    }
}
