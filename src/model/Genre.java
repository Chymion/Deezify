package model;

import java.util.ArrayList;

public class Genre {

    private String                  nom  = "";
    private String                  url  = "";
    private ArrayList<ListeMusique> list = null;

    public Genre( String nom, String url ) {

        this.nom = nom;
        this.url = url;
        this.setList( new ArrayList<ListeMusique>() );

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

    public void ajout( ListeMusique l ) {
        getList().add( l );
    }

    public static void main( String[] args ) {

    }

    public ArrayList<ListeMusique> getList() {
        return list;
    }

    public void setList( ArrayList<ListeMusique> list ) {
        this.list = list;
    }

}
