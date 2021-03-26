package model;

import java.util.ArrayList;
import java.util.List;

/**
 * Contient un nom et son url pour savoir son chemin
 * 
 * @author guill
 *
 */

public class Genre {

    private String         nom         = "";
    private String         url         = "";
    private ArrayList<Playlist> tabPlaylist = new ArrayList<Playlist>();
    private List<Album>    tabAlbum    = new ArrayList<Album>();

    /**
     * Constructeur
     * 
     * @param nom
     * @param url
     */

    public Genre( String nom, String url ) {

        this.nom = nom;
        this.url = url;

    }

    /**
     * Ajout d'un objet Playlist à l'attribut tabPlaylist
     * 
     * @param p
     *            objet Playlist
     */

    public void addPlaylist( Playlist p ) {
        tabPlaylist.add( p );
    }

    /**
     * Ajout d'un objet Album à l'attribut tabAlbum
     * 
     * @param a
     *            objet Album
     */

    public void addAlbum( Album a ) {
        tabAlbum.add( a );
    }

    // GETTERS ET SETTERS -----------------------------------------------
    // -------------------------------------------------------------------

    /**
     * 
     * @return tabPlaylist
     */

    public List<Playlist> getTabPlaylist() {
        return this.tabPlaylist;
    }

    /**
     * 
     * @return tabAlbum
     */

    public List<Album> getTabAlbum() {
        return this.tabAlbum;
    }

    /**
     * 
     * @return nom
     */

    public String getNom() {
        return nom;
    }

    /**
     * 
     * @param nom
     */

    public void setNom( String nom ) {
        this.nom = nom;
    }

    /**
     * 
     * @return url le chemin
     */

    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     */

    public void setUrl( String url ) {
        this.url = url;
    }

}
