package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;
import model.EnsembleGenre;
import model.Musique;
import model.Playlist;
import model.Utilisateur;

public class ServletListeMusiqueMaMusique extends HttpServlet {

    private static final long  serialVersionUID = 1L;
    boolean                    count            = false;
    public static float        volume;
    private List<Musique>      tabMusique       = new ArrayList<Musique>();

    public static final String CHAMP_LISTE      = "nomListe";
    public static AudioMaster  am               = new AudioMaster();

    boolean                    firstClick       = false;
    public EnsembleGenre       ensembleGenre    = null;
    public static boolean      isPlaying        = false;
    public static float        pitch;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if ( session.getAttribute( "utilisateur" ) == null && request.getParameter( "editerPlaylist" ) == null ) {
            response.sendRedirect( request.getContextPath() + "/Accueil" );
        } else {

            // Actualisation de la page
            session.setAttribute( "nomPage", "ListeMusiqueMaMusique" );

            // On récupère la liste souhaité à être modifiée
            if ( request.getParameter( "nomListeMaMusique" ) != null ) {
                session.setAttribute( "nomListeMaMusique", request.getParameter( "nomListeMaMusique" ) );
            }

            // On récupère les musiques dont l'utilisateur possède dans sa
            // playlist

            try {
                tabMusique = Playlist.getMusiqueActuel( (String) session.getAttribute( "nomListeMaMusique" ),
                        ( (Utilisateur) session.getAttribute( "utilisateur" ) ).getPseudo() );
            } catch ( Exception e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            session.setAttribute( "tabMusiqueMaMusique", tabMusique );

            if ( request.getParameter( "boutonPlay" ) != null ) {
                if ( (boolean) ( session.getAttribute( "count" ) ) == false ) {
                    ( (AudioMaster) session.getAttribute( "audio" ) ).pause();
                    session.setAttribute( "count", true );
                } else {
                    ( (AudioMaster) session.getAttribute( "audio" ) ).continuer();
                    session.setAttribute( "count", false );
                }
            }

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

            // Gestion de la musique
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

            // Préparation des attributs

            request.setAttribute( "tabMusique", tabMusique );
            request.setAttribute( "nomListe", (String) session.getAttribute( "nomListeMaMusique" ) );

            this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusiqueMaMusique.jsp" ).forward( request,
                    response );
        }
    }
}
