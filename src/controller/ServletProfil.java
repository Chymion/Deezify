package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.AudioMaster;

@WebServlet(name = "ServletProfil")
public class ServletProfil extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String nomPage="Profil";
        HttpSession session = request.getSession();
        session.setAttribute("nomPage", nomPage);
        if (request.getParameter("boutonPlay") != null)
        {
        	if ((boolean)(session.getAttribute("count")) == false)
        	{
        		((AudioMaster) session.getAttribute("audio")).pause();
        		session.setAttribute("count", true);
        	}
        	else
        	{
        		((AudioMaster) session.getAttribute("audio")).continuer();
        		session.setAttribute("count", false);
        	}
        }
    	this.getServletContext().getRequestDispatcher("/WEB-INF/Profil.jsp").forward(request, response);
    }

}
