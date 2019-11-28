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

import model.AudioMaster;
import model.DatabaseConnection;

public class ServletMusique extends HttpServlet {

    public static final String CHAMP_LISTE = "nomListe";
    private AudioMaster       am               = null;
    int count =0;
    boolean firstClick = false;
    boolean ultimateBool = false;
    boolean ultimateDestroy = false;
    Thread t;
    @SuppressWarnings("deprecation")
	protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // On récupère le nom de la playlist ou de l'album selectionné
        String nomListe = request.getParameter( CHAMP_LISTE );
        
        if (!ultimateBool)
        {
        	am = new AudioMaster();
        	ultimateBool = true;
        }
        if ( request.getParameter( "0" ) != null ) {
        	if (!firstClick)
        	{
        		firstClick = true;
        		count = 0;
        	}
        	if(count==0) {
        		if (!ultimateDestroy)
        		{
            	    am.init();
                    am.setSongName("Enter Sandman");
                    t = new Thread(am);
                    t.start();
                    count+=2;
                    ultimateDestroy = true;
        		}
        		else
        		{
        			am.Destruction();
            	    am.init();
                    am.setSongName("Enter Sandman");
                    t = new Thread(am);
                    t.start();
                    count+=2;
        		}
        		
        	}
        	else {
        		if((count&1)==0) {
        			am.pause();
        			count++;
        		}
        		else {
        			am.continuer();
        			count++;
        		}
        	}
            
        }
        
        if ( request.getParameter( "1" ) != null ) {
        	if (!firstClick)
        	{
        		//firstClick = true;
        		count = 0;
        	}
        	if(count==0) {
        		if (!ultimateDestroy)
        		{
            	    am.init();
                    am.setSongName("Numb");
                    t = new Thread(am);
                    t.start();
                    count+=2;
                    ultimateDestroy = true;
        		}
        		else
        		{
        			am.Destruction();
            	    am.init();
                    am.setSongName("Numb");
                    t = new Thread(am);
                    t.start();
                    count+=2;
        		}
        	}
        	else {
        		if((count&1)==0) {
        			am.pause();
        			count++;
        		}
        		else {
        			am.continuer();
        			count++;
        		}
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

        List<Map<String, String>> tabListe = new ArrayList<Map<String, String>>();

        try {
            Map<String, String> musique = null;
            while ( reqListeMusique.next() ) {
                musique = new HashMap<String, String>();

                musique.put( "nom", reqListeMusique.getString( "NomMusique" ) );
                musique.put( "nomArtiste", reqListeMusique.getString( "NomArtiste" ) );
                musique.put( "date", reqListeMusique.getString( "Date" ) );
                musique.put( "duree", reqListeMusique.getString( "Duree" ) );
                musique.put( "lien", reqListeMusique.getString( "URL" ) );

                tabListe.add( musique );
            }
            request.setAttribute( "tabListe", tabListe );
            request.setAttribute( "nomListe", nomListe );
        } catch ( SQLException e ) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/ListeMusique.jsp" ).forward( request, response );
    }

}
