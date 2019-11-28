package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class EnsembleGenre {

    List<Genre>        tabGenre = null;
    DatabaseConnection db       = null;

    public EnsembleGenre() {
        this.tabGenre = new ArrayList<Genre>();

        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

    }
    public EnsembleGenre( List<Genre> tabGenre ) {
        this.tabGenre = tabGenre;

        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

    }

    public void ajouter( Genre g ) {
        tabGenre.add( g );
    }

    public void remplirEnsemble( HttpServletRequest request ) throws SQLException {

        // Pour recuperer des donnees (liens) avec une requete SELECT

        ResultSet reqGenre = null;
        try {
            reqGenre = db.displayData( "SELECT * FROM genre_musical" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        while ( reqGenre.next() )
            this.ajouter( new Genre( reqGenre.getString( "NomGenreMusical" ), reqGenre.getString( "Image" ) ) );

    }

    public List<Map<String, String>> setHashMap( HttpServletRequest request ) throws SQLException {

        List<Map<String, String>> tabGenre = new ArrayList<Map<String, String>>();
        Map<String, String> genre = null;

        this.remplirEnsemble( request );

        for ( int i = 0; i < this.tabGenre.size() / 2; i++ ) {
            genre = new HashMap<String, String>();
            genre.put( "lien", this.tabGenre.get( i ).getUrl() );
            genre.put( "nom", this.tabGenre.get( i ).getNom() );
            tabGenre.add( genre );
        }

        return tabGenre;
    }

    public List<Genre> getTabGenre() {
        return tabGenre;
    }

    public void setTabGenre( List<Genre> tabGenre ) {
        this.tabGenre = tabGenre;
    }

}
