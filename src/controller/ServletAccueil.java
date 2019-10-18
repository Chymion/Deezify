package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "ServletAccueil")
public class ServletAccueil extends HttpServlet {

    public void init()
    {
        AudioMaster.init();
        (new Thread (new AudioMaster())).start();
        //Etape 1 : Établissement de la connexion
        DatabaseConnection db = null;
        try {
            db = new DatabaseConnection("jdbc:mysql://localhost:3306","root", "root", "com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Etape 2 : Dès que vous êtes connecté(un message apparait pour vous dire que vous êtes connecté), instancier la base
        //Dans Uwamp avec cette instruction
        //Instancation de la base et de toutes les tables(vous mettre en paramètre le nom de la base)
        try {
            db.initialisationDataBase("Deezify");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Faire un test avec cette requête : (la table concernée doit être remplie par cette requête)
        try {
            db.makeQuery("INSERT INTO artiste VALUES ('Skillet','','Groupe de Rock')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {




        this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);



    }

}
