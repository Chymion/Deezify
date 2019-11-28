package model;

public class Genre {

    private String nom = "";
    private String url = "";

    public Genre( String nom, String url ) {

        this.nom = nom;
        this.url = url;

    }

    public String getNom() {
        return nom;
    }

    public void setNom( String nom ) {
        this.nom = nom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl( String url ) {
        this.url = url;
    }

}
