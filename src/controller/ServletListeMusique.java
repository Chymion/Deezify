package controller;

import java.io.IOException;
import java.sql.SQLException;

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

public class ServletListeMusique extends HttpServlet {

    /**
     * 
     */
    private static final long  serialVersionUID = 1L;

    public static final String CHAMP_LISTE      = "nomListe";
    public static AudioMaster  am               = new AudioMaster();
    public static boolean      count            = false;
    boolean                    firstClick       = false;
    public EnsembleGenre       ensembleGenre    = null;
    public static boolean      isPlaying        = false;
    

    @SuppressWarnings( { "null" } )
    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Récupération de la session
        HttpSession session = request.getSession();

        // Si aucune playlist ou album a était selectionnée
        if ( ( request.getParameter( "nomListe" ) == null && request.getParameter( "music" ) == null
                && request.getParameter( "boutonPlay" ) == null && request.getParameter( "boutonLow" ) == null ) &&
                request.getParameter( "boutonUp" ) == null && request.getParameter( "deconnexion" ) == null
                && request.getParameter( "boutonSlower" ) == null &&
                request.getParameter( "boutonFaster" ) == null )
            // on se redirige vers la page actuelle
            response.sendRedirect( request.getContextPath() + "/" + session.getAttribute( "nomPage" ) );

        else {

            // actualisation de la page
            session.setAttribute( "nomPage", "ListeMusique" );

            
            // On récupère le nom de la playlist ou de l'album selectionné
            String nomListe = request.getParameter( CHAMP_LISTE );

            // Si il y'a un nom déjà existant, il suffit d'actualiser nomListe
            // de
            // session
            if ( nomListe != null )
                session.setAttribute( "nomListe", nomListe );

            // Dans tous les cas, on actualise nomListe
            nomListe = (String) session.getAttribute( "nomListe" );

           
            // Gestion de la musique
            GestionFormat.gererMusique(request, session);
            
            ensembleGenre = (EnsembleGenre) session.getAttribute( "ensembleGenre" );

            // Préparation des attributs
            System.out.println( "nomListe = " + nomListe );
            request.setAttribute( "tabMusique", ensembleGenre.getListeMusique( nomListe ) );
            request.setAttribute( "nomListe", nomListe );

            // Redirection vers la page ListeMusique.jsp
            this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusique.jsp" ).forward( request, response );
        }
    }

}