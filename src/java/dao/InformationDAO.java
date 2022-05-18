/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.naming.NamingException;
import mapper.RowMapper;
import model.Information;

/**
 *
 * @author CoD
 */
public class InformationDAO extends DBContext<Information>{
    
    private final RowMapper<Information> rowMapper;

    public InformationDAO() throws NamingException {
        this.rowMapper = rs -> new Information(rs.getString("address"), rs.getString("city"), rs.getString("country"), rs.getString("tel"), rs.getString("email"), rs.getString("image"));
    }
    
    public Information getInfor() throws Exception{
        String sql = "select * from Information";
        return get(sql, rowMapper);
    }
    
}
