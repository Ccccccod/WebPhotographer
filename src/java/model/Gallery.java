
package model;


public class Gallery {
    /** Store id*/
    private int id; 
    /** Store name*/
    private String name; 
    /** Store description*/
    private String description; 
    /** Store image name*/
    private String image; 

    /**
     * Constructor.<br>
     */
    public Gallery() {
    }

    public Gallery(int id, String name, String description, String image) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }
    

    public void setDescription(String description) {
        this.description = description;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }
    
    
}
