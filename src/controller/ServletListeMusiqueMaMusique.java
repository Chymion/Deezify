package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import adaptateur.AdaptateurFormat;
import adaptateur.AudioMasterInterface;
import adaptateur.GestionFormat;
import model.AudioMaster;
import model.EnsembleGenre;
import model.Musique;
import model.Playlist;
import model.Utilisateur;
/**
 * Controlleur de la page ListeMusiqueMaMusique.jsp
 * @author Antonin
 *
 */
public class ServletListeMusiqueMaMusique extends HttpServlet {

    private static final long  serialVersionUID = 1L;
    boolean                    count            = false;

    private List<Musique>      tabMusique       = new ArrayList<Musique>();

    public static final String CHAMP_LISTE      = "nomListe";
    public static AudioMaster  am               = new AudioMaster();

    boolean                    firstClick       = false;
    public EnsembleGenre       ensembleGenre    = null;
    public static boolean      isPlaying        = false;

    public static float        volume           = 1.0f;
    public static float        pitch            = 1.0f;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        if ( session.getAttribute( "utilisateur" ) == null && request.getParameter( "editerPlaylist" ) == null ) {
            response.sendRedirect( request.getContextPath() + "/Accueil" );
        } else {

            // Actualisation de la page
            session.setAttribute( "nomPage", "ListeMusiqueMaMusique" );

            // Instancation du click si il n'existe pas
            if ( session.getAttribute( "click" ) == null )
                session.setAttribute( "click", firstClick );

            // Instanaction de l'audio si il n'existe pas
            if ( session.getAttribute( "audio" ) == null )
                session.setAttribute( "audio", am );


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
         
            GestionFormat.gererMusique(request, session);

            // Préparation des attributs

            request.setAttribute( "tabMusique", tabMusique );
            request.setAttribute( "nomListe", (String) session.getAttribute( "nomListeMaMusique" ) );

            this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusiqueMaMusique.jsp" ).forward( request,
                    response );
        }
    }
}
