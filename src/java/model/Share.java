
package model;


public class Share {
    /** Store icon*/
    private String icon; 
    /** Store social network*/
    private String socialNetwork; 
    /** Store the url*/
    private String url; 


    public Share() {
    }


    public Share(String icon, String socialNetwork, String url) {
        this.icon = icon;
        this.socialNetwork = socialNetwork;
        this.url = url;
    }

    public String getIcon() {
        return icon;
    }


    public void setIcon(String icon) {
        this.icon = icon;
    }


    public String getSocialNetwork() {
        return socialNetwork;
    }


    public void setSocialNetwork(String socialNetwork) {
        this.socialNetwork = socialNetwork;
    }


    public String getUrl() {
        return url;
    }


    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
