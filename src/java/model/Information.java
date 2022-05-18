
package model;


public class Information {
    /**Store address*/
    private String address; 
    /**Store city*/
    private String city; 
    /**Store country*/
    private String country; 
    /**Store tel*/
    private String tel; 
    /**Store email*/
    private String email; 
    /**Store image*/
    private String image; 


    public Information() {
    }


    public Information(String address, String city, String country, String tel, String email, String image) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.tel = tel;
        this.email = email;
        this.image = image;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public String getCountry() {
        return country;
    }


    public void setCountry(String country) {
        this.country = country;
    }


    public String getTel() {
        return tel;
    }


    public void setTel(String tel) {
        this.tel = tel;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getImage() {
        return image;
    }


    public void setImage(String image) {
        this.image = image;
    }
    
    
}
