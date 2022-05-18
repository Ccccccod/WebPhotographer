/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.naming.NamingException;
import mapper.RowMapper;
import model.Gallery;

/**
 * 
 * @author CoD
 */
public class GalleryDAO extends DBContext<Gallery> {

    private final RowMapper<Gallery> rowMapper;

    public GalleryDAO() throws NamingException {
        this.rowMapper = rs -> new Gallery(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("image"));
    }
    
    public List<Gallery> getTopGallery(int count) throws Exception {
        String sql = "Select top (?) * from Gallery";
        return list(sql, rowMapper, count);
    }

    public List<Gallery> pagging(int pageIndex, int pageSize) throws Exception {
        int from = (pageIndex - 1) * pageSize + 1;
        int to = pageIndex * pageSize;
        String sql = "select * from (select ROW_NUMBER() over (order by id ASC) as No,\n"
                + " * from Gallery) as x where No between ? and ?";
        return list(sql, rowMapper, from, to);
    }

    public int numberOfResult() throws Exception {
        String sql = "Select count(id) from Gallery";
        return count(sql);
    }

    public Gallery getGalleryByID(int id) throws Exception {
        String sql = "select * from Gallery where id = ?";
        List<Gallery> gallerys = list(sql, rowMapper, id);
        return gallerys.isEmpty() ? null : gallerys.get(0);
    }
}
