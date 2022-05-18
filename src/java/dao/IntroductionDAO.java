/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.naming.NamingException;
import mapper.RowMapper;
import model.Introduction;

/**
 *
 * @author CoD
 */
public class IntroductionDAO extends DBContext<Introduction>{
    
    private final RowMapper<Introduction> rowMapper;

    public IntroductionDAO() throws NamingException {
        this.rowMapper = rs -> new Introduction(rs.getString("image"), rs.getString("entry"), rs.getString("aboutme"));
    }
    
    public Introduction getIntroduction() throws Exception{
        String sql = "select * from Intro";
        return get(sql, rowMapper);
    }
    
}
