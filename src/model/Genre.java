package model;

import java.util.ArrayList;
import java.util.List;

public class Genre {

    private String         nom         = "";
    private String         url         = "";
    // private ArrayList<ListeMusique> list = null;

    private List<Playlist> tabPlaylist = new ArrayList<Playlist>();
    private List<Album>    tabAlbum    = new ArrayList<Album>();

    public Genre( String nom, String url ) {

        this.nom = nom;
        this.url = url;

    }

    public void addPlaylist( Playlist p ) {
        tabPlaylist.add( p );
    }

    public void addAlbum( Album a ) {
        tabAlbum.add( a );
    }

    public List<Playlist> getTabPlaylist() {
        return this.tabPlaylist;
    }

    public List<Album> getTabAlbum() {
        return this.tabAlbum;
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

    public static void main( String[] args ) {

    }

}
