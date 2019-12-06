package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;
import model.EnsembleGenre;

public class ServletMusique extends HttpServlet {

    public static final String CHAMP_LISTE   = "nomListe";
    public static AudioMaster  am            = new AudioMaster();
    public static boolean      count         = false;
    boolean                    firstClick    = false;
    public EnsembleGenre       ensembleGenre = null;

    @SuppressWarnings( { "null" } )
    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Récupération de la session
        HttpSession session = request.getSession();
        session.setAttribute( "audio", am );
        session.setAttribute( "count", count );

        // On récupère le nom de la playlist ou de l'album selectionné
        String nomListe = request.getParameter( CHAMP_LISTE );

        // Si il y'a un nom déjà existant, il suffit d'actualiser nomListe de
        // session
        if ( nomListe != null )
            session.setAttribute( "nomListe", nomListe );

        // Dans tous les cas, on actualise nomListe
        nomListe = (String) session.getAttribute( "nomListe" );

        // Création de l'objet ensembleGenre en session si il n'existe pas
        if ( session.getAttribute( "ensembleGenre" ) == null ) {
            EnsembleGenre e = new EnsembleGenre();
            try {
                e.remplir();
            } catch ( SQLException e1 ) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            session.setAttribute( "ensembleGenre", e );
        }

        // Gestion de la musique
        if ( request.getParameter( "music" ) != null ) {
            if ( !firstClick ) {
            	request.setAttribute("blod", true);
                firstClick = true;
                am.init();
                am.setSongName( request.getParameter( "music" ) );
                am.demarrer();
            } else {
            	request.setAttribute("blod", true);
                count = false;
                am.Destruction();
                am.init();
                am.setSongName( request.getParameter( "music" ) );
                am.demarrer();
            }
        }
        if ( request.getParameter( "boutonPlay" ) != null ) {
            if ( count == false ) {
                am.pause();
                count = true;
            } else {
                am.continuer();
                count = false;
            }
        }

        ensembleGenre = (EnsembleGenre) session.getAttribute( "ensembleGenre" );

        request.setAttribute( "tabMusique", ensembleGenre.getListeMusique( nomListe ) );
        request.setAttribute( "nomListe", nomListe );

        this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusique.jsp" ).forward( request, response );
    }

}
