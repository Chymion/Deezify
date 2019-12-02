package model;

import java.util.ArrayList;

public class Genre {

    private String nom = "";
    private String url = "";
    ArrayList<ListeMusique>list=null;

    public Genre( String nom, String url ) {

        this.nom = nom;
        this.url = url;
        list=new  ArrayList<ListeMusique>();

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
    public void ajout(ListeMusique l) {
    	list.add(l);
    }
    public static void main (String[]args) {
    	
    }

}
