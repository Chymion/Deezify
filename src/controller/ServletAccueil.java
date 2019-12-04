package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AudioMaster;

@WebServlet( name = "ServletAccueil" )
public class ServletAccueil extends HttpServlet {

    private static final long serialVersionUID = 1L;


    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {


        /*
         * // Faire un test avec cette requete : (la table concerne doit // etre
         * // remplie par cette requete) try { db.insertData(
         * "INSERT INTO artiste VALUES ('Skrillex','','Electro')" ); } catch (
         * SQLException e ) { e.printStackTrace(); }
         * 
         * // Pour afficher des donnees avec une requete SELECT ResultSet rs =
         * null; try { rs = db.displayData( "SELECT NomArtiste FROM artiste" );
         * } catch ( SQLException e ) { e.printStackTrace(); }
         * 
         * try { System.out.println( "Affichage des donnees : " ); while (
         * rs.next() ) System.out.println( rs.getString( "NomArtiste" ) ); }
         * catch ( SQLException e ) { e.printStackTrace(); }
         */

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
    }

}
