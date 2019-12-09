package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;
import model.EnsembleGenre;
import model.ListeMusique;

/**
 * Controlleur de la page Explorer.jsp
 * 
 * @author guill
 *
 */

@WebServlet( name = "ServletExplorer" )
public class ServletExplorer extends HttpServlet {

    private static final long  serialVersionUID = 1L;

    public static final String CHAMP_LISTE      = "nomListe";
    public static AudioMaster  am               = new AudioMaster();
    public static boolean      count            = false;
    boolean                    firstClick       = false;
    public EnsembleGenre       ensembleGenre    = null;
    public static boolean      isPlaying        = false;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Récupération de la session
        HttpSession session = request.getSession();

        // Actualisation de la page sur laquelle l'utilisateur est
        session.setAttribute( "nomPage", "Explorer" );

        // si l'audio n'existe pas, on l'instancie une seule fois en session
        if ( session.getAttribute( "audio" ) == null )
            session.setAttribute( "audio", am );

        // de même pour le booléen firstClick
        if ( session.getAttribute( "click" ) == null )
            session.setAttribute( "click", firstClick );

        // Par défaut, le mode recherche sur la barre de recherche est désactivé
        if ( session.getAttribute( "estEnModeRecherche" ) == null )
            session.setAttribute( "estEnModeRecherche", false );

        // Si une musique a était selectionné ou si le bouton Play/Pause a était
        // cliqué et que l'utilisateur a saisi des données dans la barre
        if ( request.getParameter( "music" ) != null && session.getAttribute( "tabMusiqueRechercheSession" ) != null
                || ( (boolean) session.getAttribute( "estEnModeRecherche" )
                        && request.getParameter( "boutonPlay" ) != null ) ) {

            // Gestion de la musique lors d'une nouvelle musique selectionnée
            if ( request.getParameter( "music" ) != null ) {
                if ( session.getAttribute( "count" ) == null )
                    session.setAttribute( "count", count );
                firstClick = (boolean) session.getAttribute( "click" );
                if ( !firstClick ) {
                    firstClick = true;
                    session.setAttribute( "click", firstClick );
                    am.init();
                    am.setSongName( request.getParameter( "music" ) );
                    am.demarrer();
                } else {

                    count = false;
                    am.Destruction();
                    am.init();
                    am.setSongName( request.getParameter( "music" ) );
                    am.demarrer();
                }
                session.setAttribute( "count", count );
            }

            // Gestion Pause/Lecture de la musique en cours
            if ( request.getParameter( "boutonPlay" ) != null && session.getAttribute( "audio" ) != null ) {
                if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                    ( (AudioMaster) session.getAttribute( "audio" ) ).pause();
                    session.setAttribute( "count", true );
                } else {
                    ( (AudioMaster) session.getAttribute( "audio" ) ).continuer();
                    session.setAttribute( "count", false );
                }
            }

            request.setAttribute( "tabMusiqueRecherche", session.getAttribute( "tabMusiqueRechercheSession" ) );
            this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusique.jsp" ).forward( request,
                    response );
        }

        // On vérifie si une donnée a était rentré dans la barre de recherche,
        // si c'est le cas on affiche les résultats
        else if ( request.getParameter( "recherche" ) != null ) {

            // On instancie une liste de musiques que l'on afficheras comme
            // résultat
            ListeMusique liste = new ListeMusique();

            try {
                liste.rechercher( request );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusique.jsp" ).forward( request,
                    response );
        } else {

            // Création de l'objet ensembleGenre en session si il n'existe
            // pas
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

            // Gestion Pause/Lecture
            if ( request.getParameter( "boutonPlay" ) != null && session.getAttribute( "audio" ) != null ) {
                if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                    ( (AudioMaster) session.getAttribute( "audio" ) ).pause();
                    session.setAttribute( "count", true );
                } else {
                    ( (AudioMaster) session.getAttribute( "audio" ) ).continuer();
                    session.setAttribute( "count", false );
                }
            }

            // Désactivation du mode recherche (barre)
            session.setAttribute( "estEnModeRecherche", false );

            /*
             * Affichage des images 'genre' depuis la base de donnees
             */

            request.setAttribute( "tabGenre", session.getAttribute( "ensembleGenre" ) );

            /*
             * Redirection vers Explorer.jsp
             */
            this.getServletContext().getRequestDispatcher( "/WEB-INF/Explorer.jsp" ).forward( request, response );
        }

    }

}
