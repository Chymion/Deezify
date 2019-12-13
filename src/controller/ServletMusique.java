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
/**
 * Controlleur de la page ListeMusique.jsp
 * @author Antonin
 *
 */
public class ServletMusique extends HttpServlet {

    private static final long  serialVersionUID = 1L;

    public static final String CHAMP_LISTE      = "nomListe";
    public static AudioMaster  am               = new AudioMaster();
    public static boolean      count            = false;
    boolean                    firstClick       = false;
    public EnsembleGenre       ensembleGenre    = null;
    public static boolean      isPlaying        = false;
    public static float        volume           = 1.0f;
    public static float        pitch            = 1.0f;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // R�cup�ration de la session
        HttpSession session = request.getSession();

        // Si aucune playlist ou album a �tait selectionn�e
        if ( ( request.getParameter( "nomListe" ) == null && request.getParameter( "music" ) == null
                && request.getParameter( "boutonPlay" ) == null && request.getParameter( "boutonLow" ) == null ) &&
                request.getParameter( "boutonUp" ) == null && request.getParameter( "deconnexion" ) == null
                && request.getParameter( "boutonSlower" ) == null &&
                request.getParameter( "boutonFaster" ) == null )
            // on se redirige vers la page actuelle
            response.sendRedirect( request.getContextPath() + "/" + session.getAttribute( "nomPage" ) );

        else {

            // Actualisation de la page
            session.setAttribute( "nomPage", "ListeMusique" );

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

            // Si il y'a un nom d�j� existant, il suffit d'actualiser nomListe
            // de session
            if ( request.getParameter( CHAMP_LISTE ) != null )
                session.setAttribute( "nomListe", (String) request.getParameter( CHAMP_LISTE ) );

            // Cr�ation de l'objet ensembleGenre en session si il n'existe pas
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

            ensembleGenre = (EnsembleGenre) session.getAttribute( "ensembleGenre" );

            // Gestion de la s�lection d'une nouvelle musique
            if ( request.getParameter( "music" ) != null ) {

                // Pitch remis par d�faut lors de la s�lection d'une nouvelle
                // musique
                session.setAttribute( "pitch", 1.0f );

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

            // Gestion du bouton Play/Pause
            if ( request.getParameter( "boutonPlay" ) != null ) {
                if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                    ( (AudioMaster) session.getAttribute( "audio" ) ).pause();
                    session.setAttribute( "count", true );
                } else {
                    ( (AudioMaster) session.getAttribute( "audio" ) ).continuer();
                    session.setAttribute( "count", false );
                }

            }

            // Gestion du son
            if ( request.getParameter( "boutonLow" ) != null ) {
                volume = (float) session.getAttribute( "vol" );
                session.setAttribute( "vol", volume /= 2.3f );
                AudioMaster.setVolume( (float) session.getAttribute( "vol" ) );
            }

            if ( request.getParameter( "boutonUp" ) != null ) {
                volume = (float) session.getAttribute( "vol" );
                session.setAttribute( "vol", volume *= 2.3f );
                AudioMaster.setVolume( (float) session.getAttribute( "vol" ) );
            }

            // Gestion pitch
            if ( request.getParameter( "boutonFaster" ) != null ) {
                pitch = (float) session.getAttribute( "pitch" );
                session.setAttribute( "pitch", pitch += 0.1f );
                AudioMaster.modifierPitch( (float) session.getAttribute( "pitch" ) );

            }

            if ( request.getParameter( "boutonSlower" ) != null ) {
                pitch = (float) session.getAttribute( "pitch" );
                session.setAttribute( "pitch", pitch -= 0.1f );
                AudioMaster.modifierPitch( (float) session.getAttribute( "pitch" ) );
            }

            // Pr�paration des attributs si une playlist ou album a bien �tait
            // s�lectionn�

            request.setAttribute( "tabMusique",
                    ensembleGenre.getListeMusique( (String) session.getAttribute( "nomListe" ) ) );
            request.setAttribute( "nomListe", (String) session.getAttribute( "nomListe" ) );

            // Redirection vers la page ListeMusique.jsp
            this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusique.jsp" ).forward( request, response );
        }
    }

}