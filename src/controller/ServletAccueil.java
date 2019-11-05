package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DatabaseConnection;

@WebServlet( name = "ServletAccueil" )
public class ServletAccueil extends HttpServlet {

    private AudioMaster am = null;

    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        // Initialisation de la musique
        if ( request.getParameter( "bouton" ) != null ) { /* le bouton a était appuyé ? */
            am = new AudioMaster();
            am.init();
            ( new Thread( am ) ).start();
        }

        // Initialisation de la base et Etablissement de la connexion
        DatabaseConnection db = null;
        try {
            db = new DatabaseConnection( "jdbc:mysql://localhost:3306/Deezify", "root", "root",
                    "com.mysql.cj.jdbc.Driver" );
        } catch ( ClassNotFoundException | SQLException e ) {
        }

        // Faire un test avec cette requete : (la table concerne doit // etre
        // remplie par cette requete)
        try {
            db.insertData(
                    "INSERT INTO artiste VALUES ('Skrillex','','Electro')" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        // Pour afficher des donnees avec une requete SELECT
        ResultSet rs = null;
        try {
            rs = db.displayData( "SELECT NomArtiste FROM artiste" );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        try {
            System.out.println( "Affichage des donnees : " );
            while ( rs.next() )
                System.out.println( rs.getString( "NomArtiste" ) );
        } catch ( SQLException e ) {
            e.printStackTrace();
        }

        this.getServletContext().getRequestDispatcher( "/WEB-INF/Accueil.jsp" ).forward( request, response );
    }

}
