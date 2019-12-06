package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet( name = "ServletExplorer" )
public class ServletAddProfil extends HttpServlet {

    protected void service( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        request.getSession();

        this.getServletContext().getRequestDispatcher( "/WEB-INF/AddProfil.jsp" ).forward( request, response );
    }

}
