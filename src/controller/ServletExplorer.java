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
    public static float        volume           = 0.8f;
    public static float        pitch            = 1.0f;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Récupération de la session
        HttpSession session = request.getSession();

        // Actualisation de la page sur laquelle l'utilisateur est
        session.setAttribute( "nomPage", "Explorer" );

        // Instancation du click si il n'existe pas
        if ( session.getAttribute( "click" ) == null )
            session.setAttribute( "click", firstClick );

        // Instanaction de l'audio si il n'existe pas
        if ( session.getAttribute( "audio" ) == null )
            session.setAttribute( "audio", am );

        // Instancation du volume

        session.setAttribute( "vol", volume );

        // Instancation du pitch si il n'existe pas

        session.setAttribute( "pitch", pitch );

        // Par défaut, le mode recherche sur la barre de recherche est désactivé
        if ( session.getAttribute( "estEnModeRecherche" ) == null )
            session.setAttribute( "estEnModeRecherche", false );

        // PARTIE REDIRECTION
        /***************************************************************************************************************/

        // Si une musique a était selectionné ou si le bouton Play/Pause a était
        // cliqué et que l'utilisateur a saisi des données dans la barre
        if ( (boolean) session.getAttribute( "estEnModeRecherche" ) &&
                ( request.getParameter( "music" ) != null ||
                        request.getParameter( "boutonPlay" ) != null ||
                        request.getParameter( "boutonUp" ) != null ||
                        request.getParameter( "boutonLow" ) != null ||
                        request.getParameter( "boutonFaster" ) != null
                        || request.getParameter( "boutonSlower" ) != null ) ) {

            // GESTION MUSIQUE
            // ******************************************************/
            
        	AudioMaster.startSong(request, session);
          

            // ******************************************************/

            request.setAttribute( "tabPlaylistRecherche", session.getAttribute( "tabPlaylistRechercheSession" ) );
            request.setAttribute( "tabMusiqueRecherche", session.getAttribute( "tabMusiqueRechercheSession" ) );
            this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusique.jsp" ).forward( request,
                    response );
        }

        // PARTIE BOUTON RECHERCHE
        /***************************************************************************************************************/

        // On vérifie si une donnée a était rentré dans la barre de recherche,
        // si c'est le cas on affiche les résultats
        else if ( request.getParameter( "recherche" ) != null ) {

            // On instancie une liste de musiques que l'on afficheras comme
            // résultat
            ListeMusique liste = new ListeMusique();

            request.getSession().setAttribute( "estEnModeRecherche", true );

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

            request.getSession().setAttribute( "estEnModeRecherche", false );

            // Gestion Pause/Lecture
            AudioMaster.gestionEvenements(request, session);
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
