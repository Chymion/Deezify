package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;
import model.DatabaseConnection;

public class ServletMusique extends HttpServlet {

    public static final String CHAMP_LISTE = "nomListe";
    private AudioMaster        am          = new AudioMaster();
    int                        count       = 0;
    boolean                    firstClick  = false;

    @SuppressWarnings( { "null" } )
    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // On récupère le nom de la playlist ou de l'album selectionné
        String nomListe = request.getParameter( CHAMP_LISTE );

        // Récupération de la session
        HttpSession session = request.getSession();

        // Si il y'a un nom déjà existant, il suffit d'actualiser nomListe de
        // session
        if ( nomListe != null )
            session.setAttribute( "nomListe", nomListe );

        // Dans tous les cas, on actualise nomListe
        nomListe = (String) session.getAttribute( "nomListe" );

        if ( request.getParameter( "music" ) != null ) {
            if ( !firstClick ) {
                firstClick = true;
                am.init();
                am.setSongName( request.getParameter( "music" ) );
                am.demarrer();
            } else {
                am.Destruction();
                am.init();
                am.setSongName( request.getParameter( "music" ) );
                am.demarrer();
            }
        }

        /*
         * Instancation de la base
         */

        DatabaseConnection db = null;
        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( Exception e ) {
            e.getMessage();
        }

        /*
         * Requête pour récupérer les musiques de la liste
         */

        ResultSet reqListeMusique = null;

        try {
            reqListeMusique = db
                    .displayData(
                            "SELECT musique.NomMusique, Duree, Date, URL, composer.NomArtiste "
                                    + "FROM musique INNER JOIN appartient ON musique.NomMusique = appartient.NomMusique "
                                    + "INNER JOIN composer ON musique.NomMusique = composer.NomMusique "
                                    + "WHERE appartient.NomPlaylist = \"" + nomListe + "\"" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        /*
         * On rentre dans un HashMap les données correspondante
         */

        ArrayList<String> tabNomMusique = new ArrayList<String>();
        List<Map<String, String>> tabListe = new ArrayList<Map<String, String>>();

        try {
            Map<String, String> musique = null;
            while ( reqListeMusique.next() ) {

                musique = new HashMap<String, String>();
                tabNomMusique.add( reqListeMusique.getString( "NomMusique" ) );
                musique.put( "nom", reqListeMusique.getString( "NomMusique" ) );
                musique.put( "nomArtiste", reqListeMusique.getString( "NomArtiste" ) );
                musique.put( "date", reqListeMusique.getString( "Date" ) );
                musique.put( "duree", reqListeMusique.getString( "Duree" ) );
                musique.put( "lien", reqListeMusique.getString( "URL" ) );

                tabListe.add( musique );
            }

            // On prépare les attributs pour la page jsp
            request.setAttribute( "tabNomMusique", tabNomMusique );
            request.setAttribute( "tabListe", tabListe );
            request.setAttribute( "nomListe", nomListe );

        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusique.jsp" ).forward( request, response );
    }

}
