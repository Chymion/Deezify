package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AudioMaster;

@WebServlet( name = "ServletMaMusique" )
public class ServletMaMusique extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private AudioMaster       am               = null;
    int count =0;

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

     
     
        this.getServletContext().getRequestDispatcher( "/WEB-INF/MaMusique.jsp" ).forward( request, response );
    }

}
