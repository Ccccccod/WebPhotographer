/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.naming.NamingException;
import mapper.RowMapper;
import model.Share;

/**
 *
 * @author CoD
 */
public class ShareDAO extends DBContext<Share>{
    
    private final RowMapper<Share> rowMapper;

    public ShareDAO() throws NamingException {
        this.rowMapper = rs -> new Share(rs.getString("icon"), rs.getString("social_network"), rs.getString("url"));
    }
    
    public List<Share> getShare() throws Exception{
        String sql = "select * from Share";
        return list(sql, rowMapper);
    }
}
