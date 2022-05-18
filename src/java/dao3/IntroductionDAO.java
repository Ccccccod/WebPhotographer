
package dao3;

import context.OldDBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import model.Introduction;


public class IntroductionDAO {
    
    /**
     * getIntroduction.<br>
     * 
     * @return the introduction.
     */
    public Introduction getIntroduction() throws Exception{
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        OldDBContext db = new OldDBContext();
        String sql = "select * from Intro";
        
        try {
            conn = db.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Introduction intro = new Introduction(rs.getString("image"),
                                                        rs.getString("entry"),
                                                        rs.getString("aboutme"));
                
                return intro;
            }
            
        } catch (Exception ex) {
            throw ex;
        }finally{
            db.closeConnection(rs, pstmt, conn);
        }
        
        return null;
    }
}
