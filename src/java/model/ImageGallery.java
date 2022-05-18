
package model;


public class ImageGallery {
    /**Store image*/
    private String image; 
    /**Store gallery id*/
    private int galleryId; 

    /**
     * Constructor.<br>
     */
    public ImageGallery() {
    }


    public ImageGallery(String image, int galleryId) {
        this.image = image;
        this.galleryId = galleryId;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }


    public int getGalleryId() {
        return galleryId;
    }

    public void setGalleryId(int galleryId) {
        this.galleryId = galleryId;
    }
    
    
}
