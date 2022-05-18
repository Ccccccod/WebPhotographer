/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.naming.NamingException;
import mapper.RowMapper;
import model.ImageGallery;

/**
 *
 * @author CoD
 */
public class ImageGalleryDAO extends DBContext<ImageGallery>{
    
    private final RowMapper<ImageGallery> rowMapper;

    public ImageGalleryDAO() throws NamingException {
        this.rowMapper = rs -> new ImageGallery(rs.getString("image"), rs.getInt("gallery_id"));
    }
    
    public List<ImageGallery> getImageByGallery(int id) throws Exception {
        String sql = "select * from ImageGallery where gallery_id = ?";
        return list(sql, rowMapper, id);
    }
    
    public List<ImageGallery> pagging(int pageIndex, int pageSize, int galleryId) throws Exception {
        int from = (pageIndex - 1) * pageSize + 1;
        int to = pageIndex * pageSize;
        String sql = "select * from (select ROW_NUMBER() over (order by gallery_id ASC) as No,\n" +
"                * from ImageGallery where gallery_id = ?) as x where No between ? and ?";
        return list(sql, rowMapper, galleryId, from, to);
    }
    
    public int numberOfResult(int galleryId) throws Exception {
        String sql = "Select count(gallery_id) from ImageGallery where gallery_id = ?";
        return count(sql, galleryId);
    }
    
}
