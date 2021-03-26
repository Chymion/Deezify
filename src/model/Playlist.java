package model;

import java.sql.ResultSet;
/*Cette classe permet de gérer les playlists
 * @author Antonin
 */
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import fabrique.objet.FabriqueObjet;
import fabrique.objet.FabriqueObjetAbstraite;

public class Playlist extends ListeMusique {

    static DatabaseConnection db;
    private String description = "";
    private static FabriqueObjetAbstraite fab = new FabriqueObjet();
    
    /**
     * Créer une playlist et fait la connection avec la base de données
     * @param nom
     * @param uti
     * @param image
     */

    public Playlist( String nom, String uti, String image ) {
        super( nom, uti );
        this.image = image;

        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

    }
    /*Permet d'ajouter une nouvelle playlist dans la base de données
     * @throws SQLException
     */

    public void ajouterPlaylist() throws SQLException {

        db.insertData(
                "INSERT INTO playlist VALUES (\"" + this.nomListe + "\", 0,\"" + this.utilisateur
                        + "\", \"\", \"\" ) " );

    }
    /**
     * renvoie une liste de playlist qui appartiennent à un utilisateur
     * @param request
     * @param user
     * @throws SQLException
     */

    public static void actualiserPlaylist( HttpServletRequest request, Utilisateur user ) throws SQLException {

        ResultSet rs = null;

        rs = db.getData( "SELECT DISTINCT * FROM playlist WHERE Pseudo = \"" + user.getPseudo() + "\"" );

        ArrayList<Playlist> tabPlaylist = new ArrayList<Playlist>();

        boolean passe = false;
        while ( rs.next() ) {

            for ( int i = 0; i < tabPlaylist.size(); i++ ) {

                if ( tabPlaylist.get( i ).getNomListe().equals( (String) rs.getString( "NomPlaylist" ) ) )
                    passe = true;

            }
            if ( passe == false ) {
                tabPlaylist.add( fab.creerPlaylist( rs.getString( "NomPlaylist" ), user.getPseudo(), "" ) );
            } else
                passe = false;
            request.getSession().setAttribute( "tabPlaylistUtilisateur", tabPlaylist );

        }

        rs.close();
    }
    
    /**
     * renvoie la liste des musiques qui ne sont pas dans la playlist donner en paramètre
     * @param nomListe
     * @param pseudo
     * @return liste de musiques
     * @throws Exception
     */
   

    public static ArrayList<Musique> getMusiqueDisponible( String nomListe, String pseudo ) throws Exception {

        ArrayList<Musique> tabMusique = new ArrayList<Musique>();
        ResultSet rs = null;

        rs = db.getData(
                "SELECT DISTINCT * FROM musique natural join composer "
                + "WHERE musique.NomMusique NOT IN "
                + "("
                + "SELECT DISTINCT Musique.NomMusique FROM musique, playlist, appartient "
                + "WHERE musique.NomMusique = appartient.NomMusique AND appartient.NomPlaylist = playlist.NomPlaylist AND playlist.Pseudo = \""
                        + pseudo + "\" AND playlist.NomPlaylist = \"" + nomListe
                        + "\" )" );

        while ( rs.next() )
            tabMusique.add( new Musique( rs.getString( "NomMusique" ), rs.getString( "Duree" ), rs.getString( "Date" ),
                    rs.getString( "URL" ), fab.creerArtiste( rs.getString( "NomArtiste" ) ) ) );

        rs.close();
        return tabMusique;

    }
    
    /**
     * renvoi tous les styles de playlist que contient une playlist
     * @param musiques
     * @return liste de String
     * @throws Exception
     */
    
    public static ArrayList<String> getstylesPlaylist( ArrayList<Musique> musiques ) throws Exception {

      ArrayList<String> styles = new ArrayList<String>();
      ResultSet rs = null;
     
      for(Musique musique : musiques)
      {
    	  rs = db.getData("SELECT DISTINCT NomGenreMusical FROM appartenir WHERE NomArtiste LIKE \"" + musique.getArtiste().getNom() + "\"");
    	  while ( rs.next() )
    		  if(!styles.contains(rs.getString("NomGenreMusical")))
    			  styles.add(rs.getString("NomGenreMusical"));
      }
      
      return styles;

    }
    
    
    /**
     * renvoie la liste des musiques qui  sont dans la playlist donner en paramètre
     * @param nomListe
     * @param pseudo
     * @return liste de musiques
     * @throws Exception
     */

    public static ArrayList<Musique> getMusiqueActuel( String nomListe, String pseudo ) throws Exception {

        ArrayList<Musique> tabMusique = new ArrayList<Musique>();
        ResultSet rs = null;

        rs = db.getData(
                "SELECT DISTINCT * FROM musique, appartient, composer WHERE musique.NomMusique = appartient.NomMusique AND composer.NomMusique = musique.NomMusique AND appartient.NomPlaylist LIKE \""
                        + nomListe
                        + "\" " );

        while ( rs.next() )
            tabMusique.add( new Musique( rs.getString( "NomMusique" ), rs.getString( "Duree" ), rs.getString( "Date" ),
                    rs.getString( "URL" ), fab.creerArtiste( rs.getString( "NomArtiste" ) ) ) );

        rs.close();
        return tabMusique;

    }
    /**
     * Permet d'ajouter une musique à une playlist
     * @param nomListe
     * @param nomMusique
     * @throws Exception
     */

    public static void ajouterMusique( String nomListe, String nomMusique ) throws Exception {

        ArrayList<Musique> tabMusique = new ArrayList<Musique>();

        db.insertData(
                "INSERT INTO appartient VALUES (\"" + nomListe + "\", \"" + nomMusique + "\" )" );

    }
    /**
     * Permet d'enlever une musique d'une playlist
     * @param nomListe
     * @param nomMusique
     * @throws Exception
     */

    public static void supprimerMusique( String nomListe, String nomMusique ) throws Exception {

        db.insertData(
                "DELETE FROM appartient WHERE NomMusique = \"" + nomMusique + "\" AND NomPlaylist = \"" + nomListe
                        + "\"" );

    }
    /**
     * Supprime une playlist en commencant par enlever toutes les musiques quelle contient
     * @param nomListe
     * @throws Exception
     */

    public static void supprimerPlaylist( String nomListe ) throws Exception {

        // On supprime tous les tuples de appartient de la playlist nomListe

        db.insertData(
                "DELETE FROM appartient WHERE NomPlaylist = \"" + nomListe
                        + "\"" );

        // On supprime le tuple de la table playlist concerné

        db.insertData(
                "DELETE FROM playlist WHERE NomPlaylist = \"" + nomListe
                        + "\"" );

    }
    
    /**
     * renvoi la description des styles musicaux que contient une playlist (Design Pattern Décorateur)
     * @return description
     */


    public String getDescription( ) {
        return this.description;
    }
    
}